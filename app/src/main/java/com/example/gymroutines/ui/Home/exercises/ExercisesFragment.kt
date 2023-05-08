package com.example.gymroutines.ui.Home.exercises

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentCatalogExercisesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesFragment: Fragment(R.layout.fragment_catalog_exercises) {
    private var _binding: FragmentCatalogExercisesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: ExercisesViewModel by viewModels()
    private lateinit var adapter: ExercisesCatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogExercisesBinding.bind(view)
        _navControllerHome = view.findNavController()
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }

    private fun initUI() {
        initAdapter()
        initObservers()
    }

    private fun initObservers() {
        viewModel.exercisesCatalog.observe(viewLifecycleOwner) {
            adapter.submitList(it.toMutableList())
        }
        viewModel.addExercisesToRoutine.observe(viewLifecycleOwner) {
            addExercisesToRoutine()
            // pasar ejercicios seleccionados a CreateRoutine
        }
    }

    private fun addExercisesToRoutine() {
        //navControllerHome.navigate(R.id.action_routineCreateFragment_to_exercisesFragment)
    }

    private fun initAdapter() {
        adapter = ExercisesCatalogAdapter()
        binding.rvExercisesCatalog.adapter = adapter
    }
}