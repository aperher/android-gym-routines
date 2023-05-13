package com.example.gymroutines.ui.Home.routineDetails

import android.util.Log
import androidx.lifecycle.*
import com.example.gymroutines.data.routinedatails.RoutineDetailRepository
import com.example.gymroutines.model.RoutineDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineDetailsViewModel @Inject constructor(private val repository: RoutineDetailRepository) :
    ViewModel() {

    private var routineId : String? = null

    var isFavourite : Boolean? = null
    fun onFavouriteClicked() {
        Log.d("RoutineDetailsViewModel", "$isFavourite")
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

}