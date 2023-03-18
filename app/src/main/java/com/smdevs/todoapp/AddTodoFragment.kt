package com.smdevs.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.smdevs.todoapp.database.AppDatabase
import com.smdevs.todoapp.database.todo.TodoRepository
import com.smdevs.todoapp.databinding.FragmentAddTodoBinding
import com.smdevs.todoapp.viewmodel.TodoViewModel
import com.smdevs.todoapp.viewmodel.TodoViewModelFactory


class AddTodoFragment : Fragment() {

    private lateinit var binding: FragmentAddTodoBinding
    private lateinit var viewModel : TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_todo,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar.apply {
            this?.title = "Add Todo"
            this?.setDisplayHomeAsUpEnabled(true)
        }

        //Dao
        val dao = AppDatabase.getDatabase(requireActivity().application).todoDao
        //Repository
        val repository = TodoRepository(dao)
        //ViewModel
        val factory =TodoViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(TodoViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            com.google.android.material.R.id.navigation_bar_item_active_indicator_view -> view?.findNavController()?.navigate(R.id.action_addTodoFragment_to_homeFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}