package com.alberto.habits.onboarding.domain.usecase

import com.alberto.habits.onboarding.domain.repository.OnboardingRepository

class HasSeenOnBoardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Boolean{
        return repository.hasSeenOnboarding()
    }
}