package com.alberto.habits.home.domain.alarm

import com.alberto.habits.home.domain.Habit

interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}