/*
 * COPYRIGHT (c) 2020 Maruti Suzuki India Limited
 * All rights reserved
 *
 * This software embodies materials and concepts which are
 * confidential to Maruti Suzuki India Limited and is made available solely
 * pursuant to the terms of a written license agreement with
 * Maruti Suzuki India Limited.
 *
 * Designed and Developed by HARMAN Connected Services.
 */

package com.harman.plantcaretimer.ui.plants

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harman.plantcaretimer.data.PlantDao
import com.harman.plantcaretimer.repository.PlantsRepository

class PlantsViewModelFactory(
    private val plantDao: PlantDao
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantsViewModel(
            PlantsRepository(plantDao)
        ) as T
    }
}