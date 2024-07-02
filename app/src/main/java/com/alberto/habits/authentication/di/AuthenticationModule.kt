package com.alberto.habits.authentication.di

import com.alberto.habits.authentication.data.matcher.EmailMatcherImpl
import com.alberto.habits.authentication.data.repository.AuthenticationRepositoryImpl
import com.alberto.habits.authentication.domain.matcher.EmailMatcher
import com.alberto.habits.authentication.domain.repository.AuthenticationRepository
import com.alberto.habits.authentication.domain.usecase.GetUserIdUseCase
import com.alberto.habits.authentication.domain.usecase.LoginUseCases
import com.alberto.habits.authentication.domain.usecase.LoginWithEmailUseCase
import com.alberto.habits.authentication.domain.usecase.SignupUseCases
import com.alberto.habits.authentication.domain.usecase.SignupWithEmailUseCase
import com.alberto.habits.authentication.domain.usecase.ValidateEmailUseCase
import com.alberto.habits.authentication.domain.usecase.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideSignupUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): SignupUseCases {
        return SignupUseCases(
            signupWithEmailUseCase = SignupWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

}