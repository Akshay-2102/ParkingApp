package com.aks.parkingapp.presentation.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.aks.parkingapp.R
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    title = { Text("New user Creation") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,          // background color
                        titleContentColor = Color.Black        // title text color
                    )
                )
            }
        ) { innerPadding ->
            // Screen content
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                //  Greeting()

                Column(modifier = Modifier.fillMaxSize()) {


                   // ImagePickAndShow()

                    CameraWithPermissionExample()


                }

            }
        }

    }
}


//@Composable
//fun ImagePickAndShow(){
//
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        imageUri = uri
//    }
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        // Show image if selected
//        imageUri?.let {
//            Image(
//                painter = rememberAsyncImagePainter(it),
//                contentDescription = "Selected Image",
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(bottom = 16.dp),
//                contentScale = ContentScale.Crop
//            )
//        }
//
//        Button(onClick = {
//            launcher.launch("image/*")
//        }) {
//            Text("Pick Image")
//        }
//    }
//
//}

@Composable
fun CameraWithPermissionExample() {

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Create image URI
    fun createImageUri(): Uri {
        val imageFile = File(
            context.cacheDir,
            "camera_${System.currentTimeMillis()}.jpg"
        )
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
    }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (!success) {
            imageUri = null
        }
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            imageUri = createImageUri()
            cameraLauncher.launch(imageUri!!)
        }
    }

    fun checkAndOpenCamera() {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                imageUri = createImageUri()
                cameraLauncher.launch(imageUri!!)
            }

            else -> {
                // Request permission again on every click
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Captured Image",
                modifier = Modifier.size(220.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            checkAndOpenCamera()
        }) {
            Text("Capture Image")
        }
    }
}

