package com.ncs.todo.di

import android.app.Application
import androidx.room.Room
import com.ncs.todo.data.TodoDatabase
import com.ncs.todo.data.TodoRepository
import com.ncs.todo.data.TodoRepositryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesTodoDatabase(app:Application):TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo.db"
        ).build()
    }
    @Provides
    @Singleton
    fun providesTodoRepository(db:TodoDatabase):TodoRepository{
        return TodoRepositryImpl(db.dao )
    }
}