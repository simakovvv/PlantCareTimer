package com.harman.plantcaretimer.repository

import com.harman.plantcaretimer.data.CareScedule
import com.harman.plantcaretimer.data.Plant
import com.harman.plantcaretimer.data.PlantDao
import kotlinx.coroutines.*
import javax.security.auth.callback.Callback

class PlantsRepository(
    private val plantDao: PlantDao
) : CoroutineScope {

    override val coroutineContext = Dispatchers.Default + SupervisorJob()

    fun getPlantsList() = plantDao.getAll()

    fun getRaw(callback: (List<Plant>) -> Unit) = launch {
        withContext(Dispatchers.IO) {
            callback(plantDao.getAllRaw())
        }
    }

    //val careSchedule = CareScedule

    fun addPlant(plant: Plant) = launch {
        withContext(Dispatchers.IO) {
            plantDao.insert(plant)
        }
    }

    fun refreshLiveData() = launch {
        withContext(coroutineContext) {
            plantDao.refreshValues()
        }
    }
}