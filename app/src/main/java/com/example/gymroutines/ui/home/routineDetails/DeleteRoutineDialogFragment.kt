package com.example.gymroutines.ui.home.routineDetails

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteRoutineDialogFragment(private val delete :DeleteInterface): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Borrar rutina")
            .setMessage("¿Estás seguro de que quieres borrar esta rutina?")
            .setPositiveButton("Borrar") { _, _ ->
                delete.positiveButton()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                delete.negativeButton()
            }
            .create()
    }

    interface DeleteInterface {
        fun positiveButton()
        fun negativeButton()
    }
}