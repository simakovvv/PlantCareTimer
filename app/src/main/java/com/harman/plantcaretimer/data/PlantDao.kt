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

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface PlantDao {

    @Query("SELECT * FROM plant")
    fun getAll(): LiveData<List<Plant>>

    @Query("SELECT * FROM plant")
    fun getAllRaw(): List<Plant>

    @Query("DELETE FROM plant")
    fun deleteAll()

    @Delete
    fun delete(plant: Plant)

    @Update
    fun update(plant: Plant)

    @Insert
    fun insert(plant: Plant)

    @Insert
    fun insert(plant: List<Plant>)

    @Transaction
    fun updateAll(updateList: List<Plant>){
        deleteAll()
        insert(updateList)
    }

    @Transaction
    fun refreshValues() = updateAll(getAllRaw())
}
