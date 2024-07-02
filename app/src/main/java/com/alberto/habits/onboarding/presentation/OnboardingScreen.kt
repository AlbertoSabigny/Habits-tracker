package com.alberto.habits.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.alberto.habits.R
import com.alberto.habits.onboarding.components.OnboardingPager

@Composable
fun OnboardingScreen (
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish:()->Unit){
    LaunchedEffect(key1 = viewModel.hasSeenOnboarding) {
        if (viewModel.hasSeenOnboarding) {
            onFinish()
        }
    }
    val pages= listOf(
        OnBoardingPagerInfo(
            title = "pagina 1",
        subtitle = "Esta es la 1",
            image = R.drawable.onboarding1),
        OnBoardingPagerInfo(
            title = "pagina 2",
            subtitle = "Esta es la 2",
            image = R.drawable.onboarding2),
        OnBoardingPagerInfo(
            title = "pagina 3",
            subtitle = "Esta es la 3",
            image = R.drawable.onboarding3),
        OnBoardingPagerInfo(
            title = "pagina 4",
            subtitle = "Esta es la 4",
            image = R.drawable.onboarding4),
    )
    OnboardingPager(pages = pages, onFinish = { viewModel.completeOnboarding()
    })
}