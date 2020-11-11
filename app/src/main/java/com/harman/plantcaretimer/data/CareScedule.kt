package com.harman.plantcaretimer.data

import android.util.Range
import androidx.core.util.rangeTo

import java.util.*

object CareScedule {
    private val soil_1 = 1 to CareParameters(0,0, LightPeriod.LONG, GrowPeriod.CUTTING,2.5f, 2.5f,2.5f, 2,0,0,0,false ,0)
    private val soil_2 = 2 to CareParameters(0,0, LightPeriod.LONG, GrowPeriod.CUTTING,2.5f, 2.5f,2.5f, 2,0,0,0,false ,0)
    private val soil_3 = 3 to CareParameters(0,0, LightPeriod.LONG, GrowPeriod.VEG,7f, 7f,7f, 2,50,0,20,true ,0)
    private val soil_4 = 4 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,15f, 10f,5f, 0,0,0,20,true ,0)
    private val soil_5 = 5 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,15f, 10f,5f, 0,50,2,20,true ,0)
    private val soil_6 = 6 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,5f, 10f,15f, 0,0,2,0,true ,0)
    private val soil_7 = 7 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,5f, 10f,15f, 0,50,2,0,true ,0)
    private val soil_8 = 8 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,5f, 10f,15f, 0,0,2,0,true ,0)
    private val soil_9 = 9 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,5f, 10f,15f, 0,0,0,0,true ,0)
    private val soil_10 = 10 to CareParameters(0,0, LightPeriod.SHORT, GrowPeriod.FLOWERING,0f, 0f,0f, 0,0,0,0,true ,45)
    private val inSoilScedule = hashMapOf(soil_1, soil_2, soil_3, soil_4, soil_5, soil_6, soil_7, soil_8, soil_9, soil_10)

    val sceduleList = hashMapOf(FloraSeries.IN_SOIL to inSoilScedule)

    val PH_RATE = 5.5 to 6.5
    val RIPEN_PERIOD: Range<Date> = getRipenPeriod()
    val BLOOM_PERIOD: Range<Date> = getBloomPeriod()
    enum class LightPeriod {
        LONG {
            override fun toString(): String {
                return "18 hours"
            }
        },
        SHORT {
            override fun toString(): String {
                return "12 hours"
            }
        }
    }
    enum class GrowPeriod {
        CUTTING {
            override fun toString(): String {
                return "Cutting"
            }
        },
        VEG {
            override fun toString(): String {
                return "Veg"
            }
        },
        FLOWERING {
            override fun toString(): String {
                return "Flowering"
            }
        }
    }
    enum class FloraSeries {
        IN_SOIL {
            override fun toString(): String {
                return "In soil"
            }
        },
        AEROPONICS {
            override fun toString(): String {
                return "Aeroponics"
            }
        },
        COCONUT_FIBRE {
            override fun toString(): String {
                return "Coconut fibre"
            }
        },
        ROCKWOOL {
            override fun toString(): String {
                return "Rockwool"
            }
        }
    }

    private fun getBloomPeriod(): Range<Date> {
        val start = Calendar.getInstance()
        start[Calendar.WEEK_OF_YEAR] = 0
        val startDate = start.time

        val end = Calendar.getInstance()
        end[Calendar.WEEK_OF_YEAR] = 9
        val endDate = end.time

        return startDate rangeTo endDate
    }

    private fun getRipenPeriod(): Range<Date> {
        val start = Calendar.getInstance()
        start[Calendar.DAY_OF_YEAR] = 0
        val startDate = start.time

        val end = Calendar.getInstance()
        end[Calendar.DAY_OF_YEAR] = 10
        val endDate = end.time

        return startDate rangeTo endDate
    }
}