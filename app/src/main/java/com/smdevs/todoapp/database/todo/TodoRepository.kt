package com.smdevs.todoapp.database.todo

import androidx.lifecycle.LiveData

class TodoRepository(private val dao: TodoDao) {
    val allTodos = dao.getAllTodos()

    fun getTodoById (id : Int):LiveData<Todo>{
        return dao.getTodoById(id)
    }

    suspend fun insert(todo: Todo){
        dao.insert(todo)
    }

    suspend fun update(todo: Todo){
        dao.update(todo)
    }

    suspend fun delete(todo: Todo){
        dao.delete(todo)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}