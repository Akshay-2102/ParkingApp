package com.aks.parkingapp.services

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aks.parkingapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ParkingFirebaseMessagingService :
    FirebaseMessagingService() {

    override fun onNewToken(token: String) {

        super.onNewToken(token)

        Log.d(
            "FCM_TOKEN",
            token
        )
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(
        message: RemoteMessage
    ) {

        super.onMessageReceived(message)

        val title =
            message.notification?.title ?: ""

        val body =
            message.notification?.body ?: ""

        showNotification(
            title,
            body
        )
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification(
        title: String,
        body: String
    ) {

        val builder =
            NotificationCompat.Builder(
                this,
                "parking_channel"
            )
                .setSmallIcon(
                    R.drawable.ic_launcher_foreground
                )
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(
                    NotificationCompat.PRIORITY_HIGH
                )

        NotificationManagerCompat
            .from(this)
            .notify(
                100,
                builder.build()
            )
    }
}