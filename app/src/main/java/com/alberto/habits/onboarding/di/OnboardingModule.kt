package com.alberto.habits.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.alberto.habits.onboarding.data.repository.OnBoardingRepositoryImpl
import com.alberto.habits.onboarding.domain.repository.OnboardingRepository
import com.alberto.habits.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.alberto.habits.onboarding.domain.usecase.HasSeenOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {
    @Provides
    @Singleton
    fun  provideSharedPreference(@ApplicationContext context: Context):
            SharedPreferences{
        return context.getSharedPreferences("habits_onboarding_preference",
            Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository{
        return OnBoardingRepositoryImpl(sharedPreferences)    }

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardingRepository): HasSeenOnBoardingUseCase {
        return HasSeenOnBoardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardingRepository): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)
    }

}