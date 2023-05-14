package com.example.gymroutines.ui.Home.routinesCreate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutineCreateBinding
import com.example.gymroutines.model.RoutineExercisePreview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineCreateFragment : Fragment(R.layout.fragment_routine_create) {
    private var _binding: FragmentRoutineCreateBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutineCreateViewModel by activityViewModels()
    private lateinit var routineExercisesAdapter: RoutineExercisesAdapter

    private val levels = arrayOf("Fácil", "Intermedio", "Avanzado")

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
        initRoutineExercisesAdapter()
        initSpinnerAdapter()
        initObservers()
        initListeners()
        loadView()
    }

    private fun initListeners() {
        binding.btnAddExercise.setOnClickListener {
            navControllerHome.navigate(R.id.action_routineCreateFragment_to_exercisesFragment)
        }
        binding.btnGuardar.setOnClickListener {
            if (checkParameters()) {
                viewModel.createRoutine()
            }
        }
        binding.textNombreRutina.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.setName(s.toString())
            }
        })
        binding.textDescripcionRutina.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.setDescription(s.toString())
            }
        })
        binding.switchPublishRoutine.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setPublic(isChecked)
        }
        binding.textDuracion.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.setDuration(s.toString())
            }
        })
    }

    private fun initRoutineExercisesAdapter() {
        routineExercisesAdapter = RoutineExercisesAdapter { exercise ->
            viewModel.onRoutineExerciseClicked(exercise)
        }
        binding.rvRoutineExercises.adapter = routineExercisesAdapter
    }

    private fun loadView() {
        binding.textNombreRutina.setText(viewModel.routineName.value.toString())
        binding.textDescripcionRutina.setText(viewModel.routineDescription.value.toString())
        binding.switchPublishRoutine.isChecked = viewModel.routinePublic.value!!
        binding.textDuracion.setText(viewModel.routineDuration.value.toString())
    }

    private fun initSpinnerAdapter() {
        //val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levels)
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //binding.spLevel.adapter = adapter
    }

    private fun initObservers() {
        viewModel.addedExercises.observe(viewLifecycleOwner) {
            if (it != null) {
                routineExercisesAdapter.submitList(it)
            }
        }
        viewModel.goToSeries.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { exercise ->
                goToSeries(exercise)
            }
        }
    }

    private fun goToSeries(exercise: RoutineExercisePreview) {
        viewModel.setActiveExercise(exercise)
        navControllerHome.navigate(R.id.action_routineCreateFragment_to_routineExerciseSeriesFragment)
    }

    private fun checkParameters(): Boolean {
        if (viewModel.routineName.value == "") {
            Log.d("nombre", "vacia")
            return false
        } else if (viewModel.routineDescription.value == "") {
            Log.d("descripcion", "vacia")
            return false
        } else if (viewModel.addedExercises.value == null) {
            Log.d("ejercicos", "vacia")
            return false
        } else return true
    }
}

