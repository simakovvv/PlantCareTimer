package com.harman.plantcaretimer.data

import android.net.Uri
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DEF_NOTIFICATION_TIME
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DEF_START_TIME

object TestPlants {
    val imagesLib = listOf<Int>(
        R.drawable.ic_bush,
        R.drawable.ic_camomile,
        R.drawable.ic_leaves,
        R.drawable.ic_reed,
        R.drawable.ic_sprout,
        R.drawable.ic_sunflower,
        R.drawable.ic_tree,
        R.drawable.ic_wheat
    ).map { Uri.parse("android.resource://com.harman.plantcaretimer/$it") }
    val plantsList = listOf<Plant>(
        Plant("Cactus","Сад", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Cactus1","Сад", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Cactus2","Сад", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Cactus3","Сад",  DEF_START_TIME,  DEF_NOTIFICATION_TIME, false,50, imagesLib.random(), null),
        Plant("Cactus4","Сад", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Cactus5","Столовая", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Palm","Столовая", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Cactus","Столовая", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Palm","Спальня",  DEF_START_TIME,  DEF_NOTIFICATION_TIME, false,50, imagesLib.random(), null),
        Plant("Cactus","Спальня", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null),
        Plant("Palm","Спальня", DEF_START_TIME,  DEF_NOTIFICATION_TIME, false, 50, imagesLib.random(), null)
    )
}