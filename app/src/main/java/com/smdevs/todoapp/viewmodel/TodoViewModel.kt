package com.smdevs.todoapp.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.smdevs.todoapp.database.todo.Todo
import com.smdevs.todoapp.database.todo.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository,val navigate: () -> Unit) : ViewModel(),Observable {
    val allTodos = repository.allTodos

    @Bindable
    var inputTitle = MutableLiveData<String>()!!

    @Bindable
    var inputPriority = MutableLiveData<String>()!!


    public fun addHandler(){
//        Log.i("appInfo",inputTitle.value.toString())
//        Log.i("appInfo",inputPriority.value.toString())

        if(inputTitle.value == null || inputPriority.value == null){
            return
        }

        insert(Todo(0,inputTitle.value.toString(),inputPriority.value.toString().toInt(),false))

        navigate()
    }

    fun insert(todo: Todo){
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun update(todo: Todo){
        viewModelScope.launch {
            repository.update(todo)
        }
    }

    //Observable Functions
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}

class TodoViewModelFactory(val repository: TodoRepository,val navigate : ()->Unit) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(repository,{navigate()}) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}