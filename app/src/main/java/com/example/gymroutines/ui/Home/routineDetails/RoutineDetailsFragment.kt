package com.example.gymroutines.ui.Home.routineDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutineDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineDetailsFragment : Fragment(R.layout.fragment_routine_details) {
    private var _binding : FragmentRoutineDetailsBinding? = null
    private val binding get() = _binding!!


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