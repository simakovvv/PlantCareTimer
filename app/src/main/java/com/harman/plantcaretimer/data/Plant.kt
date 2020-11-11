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

package com.harman.plantcaretimer.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "number")
    var number: Int?,

    @ColumnInfo(name = "label", defaultValue = "")
    var name: String?,

    @ColumnInfo(name = "place", defaultValue = "")
    var place: String?,

    @ColumnInfo(name = "start_time", defaultValue = "")
    var startTime: String?,

    @ColumnInfo(name = "notifications_time", defaultValue = "")
    var notificationsTime: String?,

    @ColumnInfo(name = "is_scheduled", defaultValue = "0")
    var isScheduled: Boolean,

    @ColumnInfo(name = "water_level", defaultValue = "0")
    var waterLevel: Int?,

    @ColumnInfo(name = "icon_path")
    var icon: Uri,

    @ColumnInfo(name = "care_schedule")
    var careParameters: CareParameters?
) {
    constructor(
        name: String?,
        place: String?,
        startTime: String?,
        notificationsTime: String?,
        isScheduled: Boolean,
        waterLevel: Int?,
        icon: Uri,
        careParameters: CareParameters?
    ) :
            this(
                null,
                name,
                place,
                startTime,
                notificationsTime,
                isScheduled,
                waterLevel,
                icon,
                careParameters
            )
}

