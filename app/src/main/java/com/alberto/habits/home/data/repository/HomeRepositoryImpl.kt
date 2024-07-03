package com.alberto.habits.home.data.repository

import com.alberto.habits.home.data.extension.toStartOfDateTimestamp
import com.alberto.habits.home.data.local.HomeDao
import com.alberto.habits.home.data.local.mapper.toDomain
import com.alberto.habits.home.data.local.mapper.toEntity
import com.alberto.habits.home.domain.Habit
import com.alberto.habits.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

class HomeRepositoryImpl(
    private val dao: HomeDao
) : HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp())
            .map { it.map { it.toDomain() } }
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }
}