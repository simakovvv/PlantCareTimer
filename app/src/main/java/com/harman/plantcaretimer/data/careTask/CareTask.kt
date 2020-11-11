package com.harman.plantcaretimer.data.careTask

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.harman.plantcaretimer.data.Plant
import kotlinx.android.parcel.Parcelize
import java.util.*

data class CareTask(
    val name: String,
    val description: String,
    val date: String?,
    val notificationDelay: Long,
    val plant: Plant
)
