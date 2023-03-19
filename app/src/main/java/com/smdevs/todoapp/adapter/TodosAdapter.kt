package com.smdevs.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.smdevs.todoapp.R
import com.smdevs.todoapp.database.todo.Todo
import com.smdevs.todoapp.databinding.TodoItemBinding

class TodosAdapter(val todos: List<Todo>) : RecyclerView.Adapter<TodosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : TodoItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.todo_item,parent,false)

        return TodosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

class TodosViewHolder(val binding : TodoItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(todo: Todo){
        binding.apply {
            this.itemTitle.text = todo.title
            this.checkBox.isChecked = todo.checked
        }
    }
}