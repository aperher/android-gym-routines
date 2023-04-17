package com.example.gymroutines.ui.Home.routinesCreate

import androidx.lifecycle.*
import com.example.gymroutines.data.routinesCreate.RoutinesCreateRepository
import com.example.gymroutines.model.Routine
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineCreateViewModel @Inject constructor(private val repository: RoutinesCreateRepository) :
    ViewModel() {
    private var _routineName = MutableLiveData<String>("")
    private var _routineDescription = MutableLiveData<String>("")
    private var _routineExercises = MutableLiveData<List<Routine.Exercises>>()

    fun setName(routineName: String) {
        _routineName.value = routineName
    }

    fun setDescription(description: String) {
        _routineDescription.value = description
    }

    fun setExercises(exercises: List<Routine.Exercises>) {
        _routineExercises.value = exercises
    }

    fun createRoutine() {
        viewModelScope.launch {
            repository.createRoutine(
                Routine(
                    "",
                    true,
                    _routineName.value!!,
                    _routineExercises.value!!,
                    _routineDescription.value!!
                )
            )
        }
    }
}