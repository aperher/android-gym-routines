package com.example.gymroutines.ui.Home.routines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentHomeBinding
import com.example.gymroutines.databinding.FragmentRoutinesBinding
import com.example.gymroutines.model.RoutineItem
import com.example.gymroutines.model.SliderItem

class RoutinesFragment : Fragment(R.layout.fragment_routines) {
    private var _binding: FragmentRoutinesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoutinesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoutinesBinding.bind(view)

        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI() {
        val adapter = RoutinesAdapter()
        binding.recyclerView.adapter = adapter
        adapter.submitList(
            listOf<SliderItem>(
                SliderItem(
                    "id", "Creadas por mi", listOf<RoutineItem>(
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url")
                    ),
                ),
                SliderItem(
                    "id", "Rutinas guardadas", listOf<RoutineItem>(
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url")
                    ),
                ),
                SliderItem(
                    "id", "Más populares", listOf<RoutineItem>(
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url")
                    ),
                ),
                SliderItem(
                    "id", "De la comunidad", listOf<RoutineItem>(
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url"),
                        RoutineItem("id", "Mi 6 pack", "url"),
                        RoutineItem("id", "Burning abs", "url")
                    ),
                )
            )
        )
    }
}