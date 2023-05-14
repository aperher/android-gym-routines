package com.example.gymroutines.ui.home.routinesCreate

import androidx.lifecycle.*
import com.example.gymroutines.data.exercise.ExerciseRepository
import com.example.gymroutines.data.routinesCreate.RoutinesCreateRepository
import com.example.gymroutines.model.*
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RoutineCreateViewModel @Inject constructor(
    private val routineRepository: RoutinesCreateRepository,
    private val exercisesRepository: ExerciseRepository
) :
    ViewModel() {
    // Routine
    private var _goToSeries = MutableLiveData<Event<RoutineExercisePreview>>()
    val goToSeries: LiveData<Event<RoutineExercisePreview>> get() = _goToSeries

    private var _routineName = MutableLiveData<String>("")
    val routineName: LiveData<String> get() = _routineName
    private var _routineDescription = MutableLiveData<String>("")
    val routineDescription: LiveData<String> get() = _routineDescription
    private var _routinePublic = MutableLiveData<Boolean>(false)
    val routinePublic: LiveData<Boolean> get() = _routinePublic
    private var _routineDuration = MutableLiveData<String>("")
    val routineDuration: LiveData<String> get() = _routineDuration

    private var _addedExercises = MutableLiveData<List<RoutineExercisePreview>?>(null)
    val addedExercises: LiveData<List<RoutineExercisePreview>?> get() = _addedExercises

    private var _activeExercise = MutableLiveData<RoutineExercisePreview>()
    val activeExercise: LiveData<RoutineExercisePreview> get() = _activeExercise

    // Exercises
    private var _exercisesCatalog = MutableLiveData<List<Exercise>>()
    val exercisesCatalog: LiveData<List<Exercise>> get() = _exercisesCatalog

    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception

    init {
        getExercises()
    }

    fun setName(routineName: String) {
        _routineName.value = routineName
    }

    fun setDescription(description: String) {
        _routineDescription.value = description
    }

    fun setPublic(isPublic: Boolean) {
        _routinePublic.value = isPublic
    }

    fun setDuration(duration: String) {
        _routineDuration.value = duration
    }

    fun setActiveExercise(exercise: RoutineExercisePreview) {
        _activeExercise.value = exercise
    }

    fun getExercises() {
        viewModelScope.launch {
            exercisesRepository.getExercises().fold(onSuccess = {
                _exercisesCatalog.value = it
            }, onFailure = {
                exception.value = it
            })
        }
    }

    fun createRoutine() {
        viewModelScope.launch {
            routineRepository.createRoutine(getRoutine())
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
    }

    fun onRoutineExerciseClicked(exercise: RoutineExercisePreview) {
        _goToSeries.value = Event(exercise)
    }

    private fun getRoutine(): Routine {
        val strings = listOf("gym1", "gym2", "gym3", "gym4")
        val randomIndex = Random.nextInt(strings.size)
        return Routine(
            "",
            routineName.value!!,
            "Easy",
            routinePublic.value!!,
            strings[randomIndex],
            routineDescription.value!!,
            routineDuration.value!!.toInt(),
            0,
            addedExercises.value!!,
            addedExercises.value?.map { it.equipment }?.distinct() ?: listOf(),
            addedExercises.value?.map { it.primaryMuscles }?.distinct() ?: listOf(),
        )
    }

    fun resetError() {
        exception.value = null
    }
}