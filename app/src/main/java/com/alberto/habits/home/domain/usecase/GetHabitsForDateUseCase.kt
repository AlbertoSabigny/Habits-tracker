package com.alberto.habits.home.domain.usecase

import com.alberto.habits.home.domain.Habit
import com.alberto.habits.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class GetHabitsForDateUseCase(
private val repository: HomeRepository
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date)
    }
}