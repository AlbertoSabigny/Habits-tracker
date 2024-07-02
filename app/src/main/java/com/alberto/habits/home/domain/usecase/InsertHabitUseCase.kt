package com.alberto.habits.home.domain.usecase

import com.alberto.habits.home.domain.Habit
import com.alberto.habits.home.domain.repository.HomeRepository

class InsertHabitUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(habit: Habit) {
        repository.insertHabit(habit)
    }
}