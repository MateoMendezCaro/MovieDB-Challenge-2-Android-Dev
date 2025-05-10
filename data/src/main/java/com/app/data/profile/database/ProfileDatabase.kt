package com.app.data.profile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.data.profile.local.ProfileDao
import com.app.data.profile.model.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}