package com.vtlsh.universerickmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vtlsh.universerickmorty.data.local.ItemDao
import com.vtlsh.universerickmorty.data.model.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

        abstract fun urmItemDao(): ItemDao

    companion object {
        const val DATABASE_NAME = "urm_db"
    }
}