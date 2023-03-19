package com.smdevs.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.smdevs.todoapp.R
import com.smdevs.todoapp.database.todo.Todo
import com.smdevs.todoapp.databinding.TodoItemBinding

class TodosAdapter(val todos: List<Todo>,val checkHandler:(Todo,Boolean)->Unit , val longPressHandler: (Todo)->Unit) : RecyclerView.Adapter<TodosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : TodoItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.todo_item,parent,false)

        return TodosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        holder.bind(todos[position],{item:Todo,isChecked:Boolean->checkHandler(item,isChecked)},{item:Todo->longPressHandler(item)})
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

class TodosViewHolder(val binding : TodoItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(todo: Todo,checkHandler:(Todo,Boolean)->Unit,longPressHandler: (Todo) -> Unit){
        binding.apply {
            this.itemTitle.text = todo.title
            this.checkBox.isChecked = todo.checked
        }

        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            checkHandler(todo,isChecked)
        }

        binding.linearLayout.setOnClickListener {
            val isChecked=!binding.checkBox.isChecked
            checkHandler(todo,isChecked)
        }

        binding.linearLayout.setOnLongClickListener {
            longPressHandler(todo)
            true
        }
    }
}