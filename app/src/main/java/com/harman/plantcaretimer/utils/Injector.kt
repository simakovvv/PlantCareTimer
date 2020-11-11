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

package com.harman.plantcaretimer.utils

import android.content.Context
import com.harman.plantcaretimer.data.AppDatabase
import com.harman.plantcaretimer.ui.addPlant.AddPlantViewModelFactory
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModelFactory
import com.harman.plantcaretimer.ui.plants.PlantsViewModelFactory

object Injector {
    fun providePlantsViewModelFactory(context: Context) = PlantsViewModelFactory(
        AppDatabase.getInstance(context).plantsDao()
    )

    fun provideNotificationsViewModelFactory(context: Context) = NotificationsViewModelFactory(
        AppDatabase.getInstance(context).plantsDao(),
        context.getSharedPreferences("App prefs", Context.MODE_PRIVATE)
    )

    fun provideAddPlantViewModelFactory(context: Context) = AddPlantViewModelFactory(
        AppDatabase.getInstance(context).plantsDao(),
        context.getSharedPreferences("App prefs", Context.MODE_PRIVATE)
    )
}