package com.example.tp4.model.post

import android.content.Context
import com.example.tp4.model.post.room.AppDatabase
import com.example.tp4.model.post.room.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }
}
