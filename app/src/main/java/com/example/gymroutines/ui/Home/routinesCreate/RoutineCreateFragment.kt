package com.example.gymroutines.ui.Home.routinesCreate

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutineCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineCreateFragment: Fragment(R.layout.fragment_routine_create) {
    private var _binding: FragmentRoutineCreateBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutineCreateViewModel by viewModels() //REVISAR
    private lateinit var adapter: RoutineExercisesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoutineCreateBinding.bind(view)
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
        initListeners()
    }

    private fun initListeners() {
        binding.btnAddExercise.setOnClickListener {
            navControllerHome.navigate(R.id.action_routineCreateFragment_to_exercisesFragment)
        }
    }

    private fun initAdapter() {
        adapter = RoutineExercisesAdapter{ exerciseId ->
            viewModel.onExerciseClicked(exerciseId)
        }
        binding.rvRoutineExercises.adapter = adapter
    }

    private fun initObservers() {
        viewModel.goToExercise.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { exerciseId ->
                goToExerciseDetails(exerciseId)
            }
        }
    }

    private fun goToExerciseDetails(exerciseId: String) {
        val bundle = bundleOf("id" to exerciseId)
        //navControllerHome.navigate(R.id.action_routineCreateFragment_to_routineExerciseDetails, bundle)
    }
}