package com.smdevs.todoapp.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.smdevs.todoapp.database.todo.TodoRepository

class TodoViewModel(repository: TodoRepository) : ViewModel(),Observable {
    val allTodos = repository.allTodos




    //Observable Functions
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}

class TodoViewModelFactory(val repository: TodoRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}