package com.harman.plantcaretimer.ui.notifications

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.items.CareTaskItem
import com.harman.plantcaretimer.adapter.items.TitleItem
import com.harman.plantcaretimer.data.Plant
import com.harman.plantcaretimer.data.careTask.CareTask
import com.harman.plantcaretimer.notification.NotificationSchedule
import com.harman.plantcaretimer.repository.PlantsRepository
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DATE_FORMAT
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DEF_START_TIME
import com.harman.plantcaretimer.utils.CareTaskAlert
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

internal class CareTaskDateComparator : Comparator<CareTask> {
    var dateFormat = DATE_FORMAT
    override fun compare(lhs: CareTask?, rhs: CareTask?): Int {
        return dateFormat.parse(lhs?.date ?: DEF_START_TIME)
            .compareTo(dateFormat.parse(rhs?.date ?: DEF_START_TIME))
    }
}

class NotificationsViewModel(
    private val plantsRepository: PlantsRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    companion object {
        private const val MILLISECOND_IN_DAY =  86400000
        val DATE_FORMAT = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        val TIME_FORMAT = SimpleDateFormat("HH:mm", Locale.getDefault())
        var DEF_START_TIME: String = DATE_FORMAT.format(
            Calendar.getInstance().time
        )
        var DEF_NOTIFICATION_TIME = "9:00"
    }

    val careTaskList = Transformations.map(plantsRepository.getPlantsList()) {
        it.toCareTaskList()
            .sortedWith(CareTaskDateComparator())
            .filter { task ->
                val today = System.currentTimeMillis() - MILLISECOND_IN_DAY
                val taskTime = DATE_FORMAT.parse(task.date ?: DEF_START_TIME).time
                taskTime > today
            }.apply {
                forEach { task ->
                    if (!task.plant.isScheduled) {
                        task.plant.isScheduled = true
                        scheduleAll(task.plant.name ?: "Пальма", this)
                    }
                }
            }
    }

    private fun List<Plant>.toCareTaskList(): List<CareTask> {
        return mutableListOf<CareTask>().apply {
            this@toCareTaskList.forEach {
                addAll((0..6).map { iteration ->
                    CareTask(
                        "Контроль ppM",
                        "Измерьте уровень ppM что бы рассичать количество удобрений",
                        getNextDate(it.startTime ?: DEF_START_TIME, 4 * iteration),
                        getNotificationDelay(
                            it.notificationsTime ?: DEF_NOTIFICATION_TIME,
                            (4 * iteration).toLong()
                        ),
                        it
                    )
                })
            }
        }
    }

    private fun getNotificationDelay(preferredTimeOfDay: String, days: Long): Long {
        return TimeUnit.DAYS.toMillis(days)
    }

    private fun getNextDate(startDate: String, days: Int): String? {
        val c = Calendar.getInstance()
        c.time = DATE_FORMAT.parse(startDate)!!
        c.add(Calendar.DATE, days) // number of days to add
        return DATE_FORMAT.format(c.time)
    }

    fun buildList(
        updated: List<CareTask>?,
        context: Context
    ) = mutableListOf<BaseItem>().apply {
        add(TitleItem("title").apply {
            text = "Задачи по уходу"
            callback = {
                scheduleNotification(1, "test", "test")
            }
        })
        updated?.forEach { careTask ->
            add(CareTaskItem("care_task").apply {
                title = careTask.plant.name ?: ""
                subtitle = careTask.name
                date = careTask.date.toString()
                icon = careTask.plant.icon
                callback = {
                    CareTaskAlert(careTask, context) {

                    }.show()
                }
            })
        }
    }

    private fun scheduleAll(name: String, list: List<CareTask>) = list.forEach {
        scheduleNotification(it.notificationDelay, name, it.name)
    }

    private fun scheduleNotification(timeDelay: Long, tag: String, body: String) {
        val data = Data.Builder().apply {
            putString("title", tag)
            putString("body", body)
        }

        val work = OneTimeWorkRequestBuilder<NotificationSchedule>()
            .setInitialDelay(timeDelay, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder().setTriggerContentMaxDelay(1, TimeUnit.MILLISECONDS).build()
            ) // API Level 24
            .setInputData(data.build())
            .addTag(tag)
            .build()

        WorkManager.getInstance().enqueue(work)
    }
}