package com.smdevs.todoapp.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.smdevs.todoapp.R

class ConfirmDialogFragment(val callback:()->Unit) :DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Delete todo")
                .setMessage("Are you sure that you want to delete this item?")
                .setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    callback()
                })
                .setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->

                })

            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}