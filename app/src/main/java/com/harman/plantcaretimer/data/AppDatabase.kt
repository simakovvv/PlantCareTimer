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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


@Database(entities = [
    Plant::class
], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun plantsDao(): PlantDao

    companion object {
        @Volatile internal var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase  = instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): AppDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "plants-db")
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    //TODO Filling in the DB
                    CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
                       // getInstance(context).plantsDao().insert(TestPlants.plantsList)
                    }
                }
            })
            .build()
    }
}