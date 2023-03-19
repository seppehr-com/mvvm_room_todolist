package com.smdevs.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.smdevs.todoapp.adapter.TodosAdapter
import com.smdevs.todoapp.database.AppDatabase
import com.smdevs.todoapp.database.todo.Todo
import com.smdevs.todoapp.database.todo.TodoRepository
import com.smdevs.todoapp.databinding.FragmentHomeBinding
import com.smdevs.todoapp.viewmodel.TodoViewModel
import com.smdevs.todoapp.viewmodel.TodoViewModelFactory


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar.apply {
            this?.title = "Todos"
            this?.setDisplayHomeAsUpEnabled(false)
        }

        binding.floatingActionButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_addTodoFragment)
        }

        //Dao
        val todoDao = AppDatabase.getDatabase(requireActivity().application).todoDao
        //Repository
        val repository= TodoRepository(todoDao)

        //ViewModel
        val factory = TodoViewModelFactory(repository,{navigate()})
        viewModel = ViewModelProvider(this,factory).get(TodoViewModel::class.java)
        binding.viewModel=viewModel

        todosListener()

        return binding.root
    }

    private fun todosListener(){
        binding.viewModel?.allTodos?.observe(viewLifecycleOwner){
            binding.recyclerTodo.adapter=TodosAdapter(it,{item:Todo,isChecked:Boolean->itemCheckToggleHandler(item,isChecked)})
        }
    }

    public fun itemCheckToggleHandler(todo:Todo,isChecked:Boolean){
        todo.checked=isChecked
        viewModel.update(todo)
    }

    fun navigate(){

    }
}