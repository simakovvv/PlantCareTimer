package com.harman.plantcaretimer.ui.plants

import androidx.lifecycle.ViewModel
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.items.HorizontalRecyclerItem
import com.harman.plantcaretimer.adapter.items.PlantItem
import com.harman.plantcaretimer.adapter.items.TitleItem
import com.harman.plantcaretimer.data.Plant
import com.harman.plantcaretimer.repository.PlantsRepository
import kotlin.reflect.KFunction0

class PlantsViewModel(
    private val plantsRepository: PlantsRepository
) : ViewModel() {

    val plantsList = plantsRepository.getPlantsList()

    fun updatePlantsLiveData() = plantsRepository.refreshLiveData()

    private fun List<Plant>.toBaseItems(kFunction0: KFunction0<Unit>): List<BaseItem> {
        val testList = mutableListOf<BaseItem>()
        forEachIndexed { i: Int, plant: Plant ->
            testList.add(PlantItem(i.toString()).apply {
                text = plant.name ?: ""
                icon = plant.icon
                callback = {
                    kFunction0()
                }
            })
        }
        return testList
    }

    fun buildList(
        plants: List<Plant>,
        kFunction0: KFunction0<Unit>
    )=  mutableListOf<BaseItem>().apply {
        add(TitleItem("plants-title").apply {
            text = "Растения"
        })
        plants.distinctBy { it.place }
            .map { it.place }
            .forEach { place ->
                add(HorizontalRecyclerItem("plants_room").apply {
                    text = place?: ""
                    callback = { }
                    itemList = plants.filter { it.place ==  place}.toBaseItems(kFunction0)
                })
            }
    }
}