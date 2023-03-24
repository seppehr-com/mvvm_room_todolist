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

    lateinit var editableTodo:Todo
    var isEditing : Boolean = false

    @Bindable
    var inputTitle = MutableLiveData<String>()!!

    @Bindable
    var inputPriority = MutableLiveData<Int>(0)

    @Bindable
    var saveButton = MutableLiveData<String>("Add Todo")


     fun editTodo(todo: Todo){
        inputTitle.value = todo.title
        inputPriority.value = todo.priority

        saveButton.value="Update"
        editableTodo = todo
        isEditing = true
    }


     fun saveHandler(){
        if(inputTitle.value == null || inputPriority.value == null){
            return
        }

        if(isEditing){
            editableTodo.apply {
                this.title=inputTitle.value.toString()
                this.priority=inputPriority.value.toString().toInt()
            }

            update(editableTodo)
        }
        else
            insert(Todo(0,inputTitle.value.toString(),inputPriority.value.toString().toInt(),false))

        navigate()
    }

    fun getTodoById(id: Int) :LiveData<Todo>{
        return  repository.getTodoById(id)
    }

    fun insert(todo: Todo){
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun delete(todo: Todo){
        viewModelScope.launch {
            repository.delete(todo)
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