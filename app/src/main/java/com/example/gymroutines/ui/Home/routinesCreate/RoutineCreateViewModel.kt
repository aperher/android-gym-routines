package com.example.gymroutines.ui.Home.routinesCreate

import android.util.Log
import androidx.lifecycle.*
import com.example.gymroutines.data.exercise.ExerciseRepository
import com.example.gymroutines.data.routinesCreate.RoutinesCreateRepository
import com.example.gymroutines.model.*
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineCreateViewModel @Inject constructor(
    private val routineRepository: RoutinesCreateRepository,
    private val exercisesRepository: ExerciseRepository
) :
    ViewModel() {
    // Routine
    private var _goToExercise = MutableLiveData<Event<String>>()
    val goToExercise: LiveData<Event<String>> get() = _goToExercise

    private var _routineName = MutableLiveData<String>("")
    val routineName: LiveData<String> get() = _routineName
    private var _routineDescription = MutableLiveData<String>("")
    val routineDescription: LiveData<String> get() = _routineDescription
    private var _routinePublic = MutableLiveData<Boolean>(false)
    val routinePublic: LiveData<Boolean> get() = _routinePublic

    private var _addedExercises = MutableLiveData<List<RoutineExercisePreview>?>(null)
    val addedExercises: LiveData<List<RoutineExercisePreview>?> get() = _addedExercises

    // Exercises
    //private var _addExercisesToRoutine = MutableLiveData<Event<String>>()
    //val addExercisesToRoutine: LiveData<Event<String>> get() = _addExercisesToRoutine

    private var _exercisesCatalog = MutableLiveData<List<Exercise>>()
    val exercisesCatalog: LiveData<List<Exercise>> get() = _exercisesCatalog

    init {
        getExercises()
    }

    fun setName(aroutineName: String) {
        _routineName.value = aroutineName
        Log.d("nombre rutina", routineName.value.toString())
    }

    fun setDescription(description: String) {
        _routineDescription.value = description
    }

    fun setPublic(isPublic: Boolean) {
        _routinePublic.value = isPublic
    }

    fun getExercises() {
        viewModelScope.launch {
            exercisesRepository.getExercises().fold(onSuccess = {
                _exercisesCatalog.value = it
            }, onFailure = {})
        }
    }

    fun createRoutine(routine: Routine) {
        viewModelScope.launch {
            routineRepository.createRoutine(routine)
        }
    }

    fun onExerciseClicked(exercise: Exercise) {
        val existingExercise = _addedExercises.value?.find { it.name == exercise.name }
        if (existingExercise == null) {
            _addedExercises.value = _addedExercises.value.orEmpty() +
                    RoutineExercisePreview(
                        exercise.name,
                        exercise.equipment,
                        exercise.primaryMuscles,
                        exercise.secundaryMuscles,
                        listOf()
                    )
        } else {
            _addedExercises.value =
                _addedExercises.value.orEmpty().filter { it.name != exercise.name }
        }
        Log.d("lista ejs desde click", addedExercises.value.toString())
    }

    fun onRoutineExerciseClicked(exercise: RoutineExercisePreview) {
        //Log.d("minifluzi", exercise.name + "chizfluzi")
    }
}