package com.example.digitaldetoxv3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [BlockProgram::class],
    version = 1,
    exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun blockProgramDao(): BlockProgramDao
    companion object {
        @Volatile
        private var Instance: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                databaseBuilder(context, MyDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}