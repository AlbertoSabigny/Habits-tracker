package com.alberto.habits.home.di

import android.content.Context
import androidx.room.Room
import com.alberto.habits.home.data.local.HomeDao
import com.alberto.habits.home.data.local.HomeDatabase
import com.alberto.habits.home.data.local.typeconverter.HomeTypeConverter
import com.alberto.habits.home.data.repository.HomeRepositoryImpl
import com.alberto.habits.home.domain.repository.HomeRepository
import com.alberto.habits.home.domain.usecase.CompleteHabitUseCase
import com.alberto.habits.home.domain.usecase.DetailUseCases
import com.alberto.habits.home.domain.usecase.GetHabitByIdUseCase
import com.alberto.habits.home.domain.usecase.GetHabitsForDateUseCase
import com.alberto.habits.home.domain.usecase.HomeUseCases
import com.alberto.habits.home.domain.usecase.InsertHabitUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideHabitDao(@ApplicationContext context: Context, moshi: Moshi): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(HomeTypeConverter(moshi)).build().dao
    }

    @Singleton
    @Provides
    fun provideHomeRepository(dao: HomeDao): HomeRepository {
        return HomeRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}