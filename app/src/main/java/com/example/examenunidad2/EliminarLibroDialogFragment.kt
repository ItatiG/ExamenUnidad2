package com.example.examenunidad2

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EliminarLibroDialogFragment (private val position: Int): DialogFragment()
{
    internal lateinit var listener: EliminarLibroDialogListener

    interface EliminarLibroDialogListener
    {
        fun onDialogPositiveClick(position: Int)
        fun onDialogNegativeClick(position: Int)
    }

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as EliminarLibroDialogListener
        }
        catch (e: ClassCastException)
        {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle("¿Eliminar libro?")
            builder.setMessage("${Singleton.dataSet.get(position).nombre}")
                .setPositiveButton("Si",
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogPositiveClick(position)
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        listener.onDialogNegativeClick(position)

                        dismiss()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}