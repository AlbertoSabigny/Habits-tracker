package com.alberto.habits.home.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.alberto.habits.home.data.alarm.AlarmHandlerImpl
import com.alberto.habits.home.data.local.HomeDao
import com.alberto.habits.home.data.local.HomeDatabase
import com.alberto.habits.home.data.local.typeconverter.HomeTypeConverter
import com.alberto.habits.home.data.remote.HomeApi
import com.alberto.habits.home.data.remote.util.base_url
import com.alberto.habits.home.data.repository.HomeRepositoryImpl
import com.alberto.habits.home.domain.alarm.AlarmHandler
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideHabitDao(@ApplicationContext context: Context): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(HomeTypeConverter()).build().dao
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(client: OkHttpClient): HomeApi {
        return Retrofit.Builder().baseUrl(base_url.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        dao: HomeDao,
        api: HomeApi,
        alarmHandler: AlarmHandler
    ): HomeRepository {
        return HomeRepositoryImpl(dao, api, alarmHandler)
    }



    @Singleton
    @Provides
    fun provideAlarmHandler(@ApplicationContext context: Context): AlarmHandler {
        return AlarmHandlerImpl(context)
    }
}