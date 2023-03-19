package com.smdevs.todoapp.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.smdevs.todoapp.R

class ModifyItemDialogFragment(val title : String) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setItems(R.array.modify_actions,
                    DialogInterface.OnClickListener { dialog, which ->
                        // The 'which' argument contains the index position
                        // of the selected item
                    })
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}