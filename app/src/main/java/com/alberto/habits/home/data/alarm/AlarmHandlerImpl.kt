package com.alberto.habits.home.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.alberto.habits.home.data.extension.toTimeStamp
import com.alberto.habits.home.domain.Habit
import com.alberto.habits.home.domain.alarm.AlarmHandler
import java.time.DayOfWeek
import java.time.ZonedDateTime

class AlarmHandlerImpl(
    private val context: Context
) : AlarmHandler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun setRecurringAlarm(habit: Habit) {
        val nextOcurrance = calculateNextOcurrance(habit)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextOcurrance.toTimeStamp(),
            createPrendingIntent(habit, nextOcurrance.dayOfWeek)
        )
    }

    private fun createPrendingIntent(habit: Habit, dayOfWeek: DayOfWeek): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.HABIT_ID, habit.id)
        }
        return PendingIntent.getBroadcast(
            context,
            habit.id.hashCode() * 10 + dayOfWeek.value,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun calculateNextOcurrance(habit: Habit): ZonedDateTime {
        val today = ZonedDateTime.now()
        var nextOcurrance = ZonedDateTime.of(today.toLocalDate(), habit.reminder, today.zone)
        if (habit.frequency.contains(today.dayOfWeek) && today.isBefore(nextOcurrance)) {
            return nextOcurrance
        }

        do {
            nextOcurrance = nextOcurrance.plusDays(1)
        } while (!habit.frequency.contains(nextOcurrance.dayOfWeek))

        return nextOcurrance
    }

    override fun cancel(habit: Habit) {
        val nextOcurrance = calculateNextOcurrance(habit)
        val pending = createPrendingIntent(habit, nextOcurrance.dayOfWeek)
        alarmManager.cancel(pending)
    }
}