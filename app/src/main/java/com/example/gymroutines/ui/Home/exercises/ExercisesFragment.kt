package com.example.gymroutines.ui.Home.exercises

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentCatalogExercisesBinding
import com.example.gymroutines.ui.Home.routinesCreate.RoutineCreateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesFragment : Fragment(R.layout.fragment_catalog_exercises) {
    private var _binding: FragmentCatalogExercisesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutineCreateViewModel by activityViewModels()
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
        initExercisesAdapter()
        initObservers()
        initListeners()
    }

    private fun initExercisesAdapter() {
        adapter = ExercisesCatalogAdapter(
            { exercise -> viewModel.onExerciseClicked(exercise) },
            viewModel.addedExercises.value ?: emptyList()
        )
        binding.rvExercisesCatalog.adapter = adapter
    }

    private fun initObservers() {
        viewModel.exercisesCatalog.observe(viewLifecycleOwner) {
            adapter.submitList(it.toMutableList())
        }
        //viewModel.addExercisesToRoutine.observe(viewLifecycleOwner) {
        //    addExercisesToRoutine()
        // pasar ejercicios seleccionados a CreateRoutine
        //}
    }

    private fun addExercisesToRoutine() {
        // devolver lista de ejercicios seleccionados
    }

    private fun initListeners() {
        binding.btnAddExercises.setOnClickListener {
            navControllerHome.navigate(R.id.action_exercisesFragment_to_routineCreateFragment)
        }
    }
}