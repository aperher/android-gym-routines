package com.example.gymroutines.ui.Home.routineDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutineDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineDetailsFragment : Fragment(R.layout.fragment_routine_details), DeleteRoutineDialogFragment.DeleteInterface, MenuProvider {
    private var _binding : FragmentRoutineDetailsBinding? = null
    private val binding get() = _binding!!
    override fun positiveButton() {
        TODO("Not yet implemented")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_routinedetails,
            menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.deleteRoutine) {
            DeleteRoutineDialogFragment(this).show(childFragmentManager, null)
            return true
        }
        else
            return false
    }

    override fun negativeButton() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoutineDetailsBinding.bind(view)

        // Borrar esto
        binding.textView2.text = arguments?.getString("id")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}