package com.alberto.habits.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alberto.habits.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.alberto.habits.onboarding.domain.usecase.HasSeenOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val hasSeenOnBoardingUseCase: HasSeenOnBoardingUseCase,
    private val completeOnboardingUseCase: CompleteOnboardingUseCase
): ViewModel(){
    var hasSeenOnboarding by mutableStateOf(hasSeenOnBoardingUseCase())
        private set

    fun  completeOnboarding(){
        completeOnboardingUseCase()
        hasSeenOnboarding = true
    }
}