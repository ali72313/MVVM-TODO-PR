package com.example.mvvm_todo_project.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_todo_project.data.TodoDao
import com.example.mvvm_todo_project.data.TodoDataBase
import com.example.mvvm_todo_project.data.constants.Constants
import com.example.mvvm_todo_project.data.repositories.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        TodoDataBase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepository(todoDao = todoDao)
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: TodoDataBase) = dataBase.todoDao()
}