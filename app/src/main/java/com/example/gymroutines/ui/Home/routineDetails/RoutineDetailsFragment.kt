package com.example.gymroutines.ui.Home.routineDetails

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
        initAdapters()
        initObservers()


    }
    private fun initAdapters() {
        Equipmentadapter = RoutineDetailEquipmentAdapter()
        binding.rvEquipment.adapter = Equipmentadapter
        Exercisesadapter = RoutineDetailExercisesAdapter()
        binding.rvExercises.adapter = Exercisesadapter
    }
    private fun initObservers() {
        viewModel.getRoutine(arguments?.getString("id")!!).observe(viewLifecycleOwner) {
            Log.d("routine", it.exercises.toString())
            binding.toolbar.title = it.title
            binding.tvRoutineLevel.text = it.level
            binding.tvDescription.text = it.description
            Equipmentadapter.submitList(it.equipment.toMutableList())
            Exercisesadapter.submitList(it.exercises.toMutableList())
            when(it.imageURL){
                "gym1" -> binding.ivRoutineImage.setImageResource(R.drawable.gym1)
                "gym2" -> binding.ivRoutineImage.setImageResource(R.drawable.gym2)
                "gym3" -> binding.ivRoutineImage.setImageResource(R.drawable.gym3)
                else -> binding.ivRoutineImage.setImageResource(R.drawable.gym4)
            }



            requireActivity().addMenuProvider(this,
                viewLifecycleOwner, Lifecycle.State.RESUMED)


        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }
}