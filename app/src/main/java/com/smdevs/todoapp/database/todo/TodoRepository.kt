package com.smdevs.todoapp.database.todo

class TodoRepository(private val dao: TodoDao) {
    val allTodos = dao.getAllTodos()

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