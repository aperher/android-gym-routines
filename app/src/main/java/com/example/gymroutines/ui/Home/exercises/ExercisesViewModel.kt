package com.example.gymroutines.ui.Home.exercises

import android.util.Log
import androidx.lifecycle.*
import com.example.gymroutines.data.exercise.ExerciseRepository
import com.example.gymroutines.model.Exercise
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val repository: ExerciseRepository) :
    ViewModel() {
    private var _addExercisesToRoutine = MutableLiveData<Event<String>>()
    val addExercisesToRoutine: LiveData<Event<String>> get() = _addExercisesToRoutine

    private var _exercisesCatalog = MutableLiveData<List<Exercise>>()
    val exercisesCatalog : LiveData<List<Exercise>> get() = _exercisesCatalog

    init {
        getExercises()
    }

    private fun getExercises() {
        viewModelScope.launch {
            Log.d("kjgk", "jimnuyghvbytfcvtf a")
            repository.getExercises().fold(onSuccess = {
                _exercisesCatalog.value = it
            }, onFailure = {
                Log.d("ha petado", "hola")
            })
        }
    }
}