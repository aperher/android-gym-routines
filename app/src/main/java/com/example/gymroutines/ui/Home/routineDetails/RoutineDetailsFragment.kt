package com.example.gymroutines.ui.Home.routineDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentCatalogRoutinesBinding
import com.example.gymroutines.databinding.FragmentRoutineDetailsBinding
import com.example.gymroutines.ui.Home.chat.ChatAdapter
import com.example.gymroutines.ui.Home.routinesCatalog.RoutinesCatalogAdapter
import com.example.gymroutines.ui.Home.routinesCatalog.RoutinesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineDetailsFragment : Fragment(R.layout.fragment_routine_details), DeleteRoutineDialogFragment.DeleteInterface, MenuProvider {
    private var _binding : FragmentRoutineDetailsBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutineDetailsViewModel by viewModels()
    private lateinit var Equipmentadapter: RoutineDetailEquipmentAdapter
    private lateinit var Exercisesadapter: RoutineDetailExercisesAdapter
    override fun positiveButton() {
       viewModel.deleteRoutine(arguments?.getString("id")!!)
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
        else if( menuItem.itemId == R.id.editRoutine) {
            var i = "SI"
            return true
        }
        else {
            return false
        }
    }

    override fun negativeButton() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoutineDetailsBinding.bind(view)
        _navControllerHome = view.findNavController()
        initUI()
    }

    private fun initUI() {

        initObservers()
        initApadters()

    }
    private fun initApadters() {
        Equipmentadapter = RoutineDetailEquipmentAdapter()
        binding.rvEquipment.adapter = Equipmentadapter
        Exercisesadapter = RoutineDetailExercisesAdapter()
        binding.rvExercisesDetails.adapter = Exercisesadapter
    }
    private fun initObservers() {
        viewModel.routine.observe(viewLifecycleOwner) {
            Equipmentadapter.submitList(it.equipment)
            Exercisesadapter.submitList(it.exercises)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}