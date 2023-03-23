package com.smdevs.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.smdevs.todoapp.adapter.TodosAdapter
import com.smdevs.todoapp.database.AppDatabase
import com.smdevs.todoapp.database.todo.Todo
import com.smdevs.todoapp.database.todo.TodoRepository
import com.smdevs.todoapp.databinding.FragmentHomeBinding
import com.smdevs.todoapp.dialog.ConfirmDialogFragment
import com.smdevs.todoapp.dialog.ModifyItemDialogFragment
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
            val bundle = bundleOf("action" to "insert")
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_addTodoFragment,bundle)
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
            binding.recyclerTodo.adapter=TodosAdapter(it,{item:Todo,isChecked:Boolean->itemCheckToggleHandler(item,isChecked)},{item:Todo->itemLongPressHandler(item)})
        }
    }

    private fun itemCheckToggleHandler(todo:Todo, isChecked:Boolean){
        todo.checked=isChecked
        viewModel.update(todo)
    }

    private fun itemLongPressHandler(todo: Todo){
        val modifyDialog = ModifyItemDialogFragment(todo) {todoItem:Todo , action: Int ->
            modifyHandler(todoItem,action)
        }
        modifyDialog.show(parentFragmentManager,"modify_item")
    }

    private fun modifyHandler(todo:Todo,action : Int){
        when(resources.getStringArray(R.array.modify_actions)[action]){
            "Edit"->{
                val bundle = bundleOf("action" to "update")
                bundle.putSerializable("todo",todo)

                view?.findNavController()?.navigate(R.id.action_homeFragment_to_addTodoFragment,bundle)
            }
            "Remove"->{
                val confirmDialog = ConfirmDialogFragment({->
                    binding.viewModel?.delete(todo)
                })

                confirmDialog.show(parentFragmentManager,"confirm_dialog")
            }
        }
    }

    fun navigate(){

    }
}