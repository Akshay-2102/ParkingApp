import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aks.parkingapp.R
import com.aks.parkingapp.presentation.ui.screens.onboarding.OnboardingPage
import com.aks.parkingapp.presentation.ui.screens.onboarding.OnboardingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingPage("Welcome", "Discover smart parking easily", R.drawable.logo),
        OnboardingPage("Find Spots", "Locate parking near you", R.drawable.logo),
        OnboardingPage("Book Instantly", "Reserve your slot quickly", R.drawable.logo)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        // 🔹 Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->

            val currentPage = pagerState.currentPage

            // Animation trigger
            val isSelected = page == currentPage

            val alpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0f,
                label = ""
            )

            val offsetY by animateDpAsState(
                targetValue = if (isSelected) 0.dp else 40.dp,
                label = ""
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = pages[page].image),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = pages[page].title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .alpha(alpha)
                        .offset(y = offsetY)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = pages[page].description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .alpha(alpha)
                        .offset(y = offsetY)
                )
            }
        }

        // 🔹 Skip Button
        Text(
            text = "Skip",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    viewModel.completeOnboarding()
                    onFinish()
                }
        )

        // 🔹 Bottom Section
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ✅ Dot Indicator with animation
            Row {
                repeat(pages.size) { index ->

                    val isSelected = pagerState.currentPage == index

                    val width by animateDpAsState(
                        targetValue = if (isSelected) 24.dp else 10.dp,
                        label = ""
                    )

                    val color by animateColorAsState(
                        targetValue = if (isSelected) Color.Black else Color.Gray,
                        label = ""
                    )

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(10.dp)
                            .width(width)
                            .clip(RoundedCornerShape(50))
                            .background(color)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Next / Get Started Button
            Button(
                onClick = {
                    if (pagerState.currentPage == pages.lastIndex) {
                        viewModel.completeOnboarding()
                        onFinish()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.lastIndex)
                        "Get Started"
                    else
                        "Next"
                )
            }
        }
    }
}