package com.harman.plantcaretimer.ui.addPlant

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.items.*
import com.harman.plantcaretimer.data.Plant
import com.harman.plantcaretimer.data.TestPlants
import com.harman.plantcaretimer.repository.PlantsRepository
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DEF_NOTIFICATION_TIME
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DEF_START_TIME
import kotlin.reflect.KFunction0

class AddPlantViewModel(
    private val plantsRepository: PlantsRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val newPlant = Plant("Симпатичная пальма", "Дом",DEF_START_TIME, DEF_NOTIFICATION_TIME, false, 0, TestPlants.imagesLib.random(), null)

    fun updatePlantsLiveData() = plantsRepository.refreshLiveData()

    fun addPlant(plant: Plant) = plantsRepository.addPlant(plant)

    fun buildList(kFunction0: KFunction0<Unit>) = mutableListOf<BaseItem>().apply {
        add(TitleItem("title1").apply {
            text = "Стандарные параметры"
        })
        add(EditTextItem("name").apply {
            title = "Название:"
            hint = "введите имя"
            callback = {
                newPlant.name = it
            }
        })

        add(EditTextItem("name").apply {
            title = "Место:"
            hint = "введите место"
            callback = {
                newPlant.place = it
            }
        })

        add(DateTimeItem("preferred_time").apply {
            title = "Время напоминания:"
            subTitle = DEF_NOTIFICATION_TIME
            type = DateTime.TIME
            callback = {
                newPlant.notificationsTime = it
            }
        })
        add(DateTimeItem("start_date").apply {
            title = "Дата начала полива:"
            subTitle = DEF_START_TIME
            callback = {
                newPlant.startTime = it
            }
        })
        add(SaveUserDataItem("save_data").apply {
            callback = {
                addPlant(newPlant)
                kFunction0()
            }
        })
    }

   /* fun buildList(context: Context) = mutableListOf<BaseItem>().apply {

        add(VerticalRecyclerItem("title1").apply {
            text = "Стандарные параметры"
            itemList = getParameters()
        })
        add(VerticalRecyclerItem("title2").apply {
            text = "Расширенные параметры"
            itemList = getExtendedParameters()
        })
        add(SaveUserDataItem("save_data").apply {
            callback = {
                Toast.makeText(context, "", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getParameters() = mutableListOf<BaseItem>().apply {
        add(EditTextItem("name").apply {
            title = "Название:"
            hint = "введите имя"
        })

        add(EditTextItem("name").apply {
            title = "Место:"
            hint = "введите место"
        })


        add(EditTextItem("water_capacity").apply {
            title = "Расход воды"
            subtitle = "(Л)"
        })

        add(EditTextItem("water_frequency").apply {
            title = "Частота полива:"
            subtitle = "(Часы)"
        })
    }

    private fun getExtendedParameters() = mutableListOf<BaseItem>().apply {
        add(EditTextItem("grow_period").apply {
            title = "Период созревания:"
            subtitle = "(Недели)"
            valueTitle = "10"
            isEditable = false
        })
        add(EditTextItem("grow_period").apply {
            title = "Возраст растения:"
            subtitle = "(Недели)"
        })
        add(SpinnerItem("select_grow_mechanic").apply {
            title = "Выберите тип флоры"
            itemsList = CareScedule.FloraSeries.values().map { it.toString()}
        })
    }*/
}