package com.vtlsh.universerickmorty.di

import android.content.Context
import androidx.room.Room
import com.vtlsh.universerickmorty.data.db.AppDatabase
import com.vtlsh.universerickmorty.data.local.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule { //local settings

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideURMItemDao(database: AppDatabase): ItemDao {
        return database.urmItemDao()
    }

}