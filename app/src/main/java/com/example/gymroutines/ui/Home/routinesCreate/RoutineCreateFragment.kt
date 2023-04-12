package com.example.gymroutines.ui.Home.routinesCreate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutineCreateBinding
import com.example.gymroutines.ui.Home.routinesCatalog.RoutinesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineCreateFragment: Fragment(R.layout.fragment_routine_create) {
    private var _binding: FragmentRoutineCreateBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutinesViewModel by viewModels() // CREAR VIEWMODEL
    private lateinit var adapter: RoutineCreateAdapter

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
    }

    private fun initObservers() {
        // observar botón 'Añade un ejercicio' para ir a la pantalla de ejercicios
    }

    private fun initAdapter() {
        adapter = RoutineCreateAdapter { routineId ->
            viewModel.onRoutineClicked(routineId)
        }
        binding.rvCreateRoutine.adapter = adapter
    }
}