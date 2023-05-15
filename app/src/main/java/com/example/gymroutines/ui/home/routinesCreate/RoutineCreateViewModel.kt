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

    /**
     * Setea el nombre de la rutina.
     * @param routineName El nombre de la rutina.
     */
    fun setName(routineName: String) {
        _routineName.value = routineName
    }

    /**
     * Setea la descripcción de la rutina.
     * @param description la descripcción de la rutina.
     */
    fun setDescription(description: String) {
        _routineDescription.value = description
    }

    /**
     * Setea si la rutina es pública o privada.
     * @param isPublic Indica si la rutina es pública.
     */
    fun setPublic(isPublic: Boolean) {
        _routinePublic.value = isPublic
    }

    /**
     * Setea la duración de la rutina.
     * @param duration La duración de la rutina.
     */
    fun setDuration(duration: String) {
        _routineDuration.value = duration
    }

    /**
     * Setea el ejercicio activo.
     * @param exercise El ejercicio activo.
     */
    fun setActiveExercise(exercise: RoutineExercisePreview) {
        _activeExercise.value = exercise
    }

    /**
     * Obtiene la lista de ejercicios desde el repositorio de ejercicios ç.
     */
    fun getExercises() {
        viewModelScope.launch {
            exercisesRepository.getExercises().fold(onSuccess = {
                _exercisesCatalog.value = it
            }, onFailure = {
                exception.value = it
            })
        }
    }

    /**
     * Crea la rutina en el repositorio de rutinas.
     */
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

    /**
     * Método para crear objeto rutina y pasarselo al repositorio para crear rutina
     * @return Objeto rutina
     */
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

    /**
     * Método para resetear el error
     */
    fun resetError() {
        exception.value = null
    }
}