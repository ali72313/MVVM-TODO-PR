package com.example.mvvm_todo_project.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.models.TodoTask
import kotlinx.coroutines.flow.Flow
import java.util.UUID


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TodoTask>>

    @Query("SELECT * FROM TODO_TABLE WHERE id=:taskId")
    fun getTask(taskId: UUID): Flow<TodoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun newTask(todoTask: TodoTask)

    @Update
    suspend fun updateTask(todoTask: TodoTask)

    @Delete
    suspend fun deleteTask(todoTask: TodoTask)

    @Query("DELETE FROM TODO_TABLE")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM TODO_TABLE WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchTask(searchQuery: String): Flow<List<TodoTask>>

    @Query("SELECT * FROM TODO_TABLE ORDER BY CASE WHEN priority LIKE 'L%' THEN 1  WHEN priority LIKE 'M%' THEN 2 WHEN  priority LIKE 'H%' THEN 3 END ")
    fun sortByLowPriority(priority: Priority): Flow<List<TodoTask>>

    @Query("SELECT * FROM TODO_TABLE ORDER BY CASE WHEN priority LIKE 'H%' THEN 1  WHEN priority LIKE 'M%' THEN 2 WHEN  priority LIKE 'L%' THEN 3 END ")
    fun sortByHighPriority(priority: Priority): Flow<List<TodoTask>>
}