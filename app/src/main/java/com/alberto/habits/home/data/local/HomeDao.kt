package com.alberto.habits.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alberto.habits.home.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabits(habitEntitys: List<HabitEntity>)

    @Query("SELECT * FROM HabitEntity WHERE id = :id")
    fun getHabitById(id: String): HabitEntity

    @Query("SELECT * FROM HabitEntity WHERE startDate <= :date")
    fun getAllHabitsForSelectedDate(date: Long): Flow<List<HabitEntity>>
}