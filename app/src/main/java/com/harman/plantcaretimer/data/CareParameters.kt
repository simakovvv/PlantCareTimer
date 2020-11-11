package com.harman.plantcaretimer.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CareParameters(
    @SerializedName("waterLevel")
    @Expose
    val waterLevel: Int = 0,
    @SerializedName("waterCapacity")
    @Expose
    val waterCapacity: Int = 0,
    @SerializedName("lightPeriod")
    @Expose
    var lightPeriod: CareScedule.LightPeriod?,
    @SerializedName("growPeriod")
    @Expose
    var growPeriod: CareScedule.GrowPeriod?,
    @SerializedName("floraGro")
    @Expose
    var floraGro: Float = 0f,
    @SerializedName("floraMicro")
    @Expose
    var floraMicro:Float = 0f,
    @SerializedName("floraBloom")
    @Expose
    var floraBloom: Float = 0f,
    @SerializedName("bioRoots")
    @Expose
    var bioRoots: Int = 0,
    @SerializedName("bioProtect")
    @Expose
    var bioProtect: Int = 5,
    @SerializedName("bioBloom")
    @Expose
    var bioBloom: Int = 0,
    @SerializedName("diamondNectar")
    @Expose
    var diamondNectar: Int = 0,
    @SerializedName("mineralMagic")
    @Expose
    var mineralMagic: Boolean = false,
    @SerializedName("ripen")
    @Expose
    var ripen: Int = 0
) : Parcelable


//https://stackoverflow.com/questions/1877417/how-to-set-a-timer-in-android
/*
Calendar calendar = Calendar.getInstance();

int tHour = calendar.get(Calendar.HOUR_OF_DAY);
int tmin = calendar.get(Calendar.MINUTE);

if (tHour = 19 && tmin > 0 && tmin<30){
    btn.setEnabled(true);
}else{
    btn.setEnabled(false);
}*/
