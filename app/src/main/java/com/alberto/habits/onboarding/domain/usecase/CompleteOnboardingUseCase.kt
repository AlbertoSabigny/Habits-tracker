package com.alberto.habits.onboarding.domain.usecase

import com.alberto.habits.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke(){
        repository.completeOnboarding()
    }
}