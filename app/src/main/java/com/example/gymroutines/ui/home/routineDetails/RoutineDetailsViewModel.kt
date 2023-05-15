package com.example.gymroutines.ui.home.routineDetails

import androidx.lifecycle.*
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.data.routineDetails.RoutineDetailRepository
import com.example.gymroutines.model.RoutineDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineDetailsViewModel @Inject constructor(
    private val repository: RoutineDetailRepository,
    private val authRepository: AuthRepository
) :
    ViewModel() {

    private var routineId: String? = null
    var isFavourite: Boolean? = null
    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception
    fun onFavouriteClicked() {
        isFavourite?.let { isFavourite ->
            if (isFavourite) {
                repository.removeFavourite(routineId!!)
            } else {
                repository.addFavourite(routineId!!)
            }
            this.isFavourite = !isFavourite
        }
    }

    /**
     * Elimina la rutina llamando al método del repositorio de rutinas.
     */
    fun deleteRoutine() {
        repository.deleteRoutine(routineId!!)
    }

    /**
     * Método para saber si el usuario actual es el autor de la rutina.
     * @return true si el usuario actual es el autor de la rutina, false en caso contrario.
     */
    fun isAuthor(routineUser: String): Boolean = routineUser == authRepository.getUser()

    /**
     * Método para obtener la rutina accediendo al repostorio de rutinas.
     * @param idRoutine El id de la rutina.
     * @return La rutina.
     */
    fun getRoutineId(idRoutine: String): LiveData<RoutineDetail> {
        routineId = idRoutine
        val routineFlow = repository.getRoutine(idRoutine)
        viewModelScope.launch {
            routineFlow.collect {
                isFavourite = it.isFavourite
            }
        }
        return routineFlow.asLiveData()
    }
}