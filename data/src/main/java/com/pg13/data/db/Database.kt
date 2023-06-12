package com.pg13.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pg13.data.entities.local.ProfileLocal

@Database(entities = [ProfileLocal::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}