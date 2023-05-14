package com.example.gymroutines.ui.Home.routineDetails

import androidx.lifecycle.*
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.data.routinedatails.RoutineDetailRepository
import com.example.gymroutines.model.RoutineDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineDetailsViewModel @Inject constructor(private val repository: RoutineDetailRepository, private val authRepository: AuthRepository) :
    ViewModel() {

    private var routineId : String? = null
    var isFavourite : Boolean? = null
    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception
    fun onFavouriteClicked() {
        isFavourite?.let{ isFavourite ->
            if (isFavourite) {
                repository.removeFavourite(routineId!!)
            } else {
                repository.addFavourite(routineId!!)
            }
            this.isFavourite = !isFavourite
        }
    }

    fun deleteRoutine() {
        repository.deleteRoutine(routineId!!)
    }

    fun isAuthor(routineUser: String) : Boolean = routineUser == authRepository.getUser()

    fun getRoutineId(idRoutine: String) : LiveData<RoutineDetail> {
        routineId = idRoutine
        val routineFlow = repository.getRoutine(idRoutine)
        viewModelScope.launch {
            routineFlow.collect {
                isFavourite = it.isFavourite
            }
        }
        return routineFlow.asLiveData()
    }

    fun resetError(){
        exception.value = null
    }

}