package com.alberto.habits.home.di

import com.alberto.habits.home.data.repository.HomeRepositoryImpl
import com.alberto.habits.home.domain.repository.HomeRepository
import com.alberto.habits.home.domain.usecase.CompleteHabitUseCase
import com.alberto.habits.home.domain.usecase.DetailUseCases
import com.alberto.habits.home.domain.usecase.GetHabitByIdUseCase
import com.alberto.habits.home.domain.usecase.GetHabitsForDateUseCase
import com.alberto.habits.home.domain.usecase.HomeUseCases
import com.alberto.habits.home.domain.usecase.InsertHabitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Singleton
    @Provides
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = GetHabitsForDateUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideDetailUseCases(repository: HomeRepository): DetailUseCases {
        return DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(repository),
            insertHabitUseCase = InsertHabitUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideHomeRepository(): HomeRepository {
        return HomeRepositoryImpl()
    }
}