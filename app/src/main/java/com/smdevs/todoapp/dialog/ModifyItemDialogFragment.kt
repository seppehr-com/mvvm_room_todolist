package com.smdevs.todoapp.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.smdevs.todoapp.R
import com.smdevs.todoapp.database.todo.Todo

class ModifyItemDialogFragment(val todo:Todo ,val modifyHandler:(Todo,Int)->Unit) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle(todo.title)
                .setItems(R.array.modify_actions,
                    DialogInterface.OnClickListener { dialog, which ->
                        // The 'which' argument contains the index position
                        // of the selected item
                        modifyHandler(todo,which)
                    })
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}