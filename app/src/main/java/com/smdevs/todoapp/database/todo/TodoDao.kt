package com.smdevs.todoapp.database.todo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * From todos_tb")
    fun getAllTodos():LiveData<List<Todo>>

    @Insert
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("Delete From todos_tb")
    suspend fun deleteAll()

    @Update
    suspend fun update(todo: Todo)
}