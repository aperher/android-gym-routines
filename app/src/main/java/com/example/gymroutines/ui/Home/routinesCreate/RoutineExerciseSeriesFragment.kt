package com.example.gymroutines.ui.Home.routinesCreate

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentExerciseSeriesBinding
import com.google.android.material.textfield.TextInputEditText

class RoutineExerciseSeriesFragment : Fragment(R.layout.fragment_exercise_series) {
    private var _binding: FragmentExerciseSeriesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutineCreateViewModel by activityViewModels()

    private var serieCount = 1
    private var seriesList : List<Int> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExerciseSeriesBinding.bind(view)
        _navControllerHome = view.findNavController()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }

    init {
        // CARGAR INFO DEL EJERCICIO SELECCIONADO
        binding.tvExerciseSeriesName.text = arguments?.getString("exerciseName") ?: ""
        binding.tvExerciseInfo.text = arguments?.getString("exerciseEquipment") ?: ""
    }

    private fun initListeners() {
        binding.btnAddSerie.setOnClickListener {
            addSerie()
        }
        binding.btnSaveSeries.setOnClickListener {
            setSeries()
            navControllerHome.navigate(R.id.action_routineExerciseSeriesFragment_to_routineCreateFragment)
        }
    }

    private fun addSerie() {
        val newLayout = LinearLayoutCompat(requireContext())
        newLayout.layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        newLayout.orientation = LinearLayoutCompat.HORIZONTAL
        binding.linearLayoutSeries.addView(newLayout)

        val tvSerieNumber = TextView(requireContext())
        tvSerieNumber.text = "Serie ${++serieCount}"
        tvSerieNumber.textSize = 16f
        tvSerieNumber.setTypeface(null, Typeface.BOLD)
        newLayout.addView(tvSerieNumber)

        val textReps = TextInputEditText(requireContext())
        textReps.layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        textReps.inputType = InputType.TYPE_CLASS_NUMBER
        newLayout.addView(textReps)
    }

    private fun setSeries() {
        viewModel.addedExercises.value!!.map { exercise ->
            if (exercise.name == "") {
               exercise.series = seriesList
            }
        }
    }
}