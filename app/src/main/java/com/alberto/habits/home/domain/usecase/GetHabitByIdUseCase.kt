package com.alberto.habits.home.domain.usecase

import com.alberto.habits.home.domain.Habit
import com.alberto.habits.home.domain.repository.HomeRepository

class GetHabitByIdUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String): Habit {
        return repository.getHabitById(id)
    }
}