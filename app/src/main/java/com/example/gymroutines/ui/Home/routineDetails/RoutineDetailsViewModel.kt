package com.example.gymroutines.ui.Home.routineDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.gymroutines.data.routinedatails.RoutineDetailRepository
import com.example.gymroutines.data.routinedatails.RoutineDetailRepositoryImpl
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogRepository
import com.example.gymroutines.model.Messages
import com.example.gymroutines.model.RoutineDetail
import javax.inject.Inject

class RoutineDetailsViewModel @Inject constructor(private val repository: RoutineDetailRepository): ViewModel() {

    val routine: LiveData<RoutineDetail> =
        repository.getRoutine("2dczqMf9fAGkt6ubFOm4").asLiveData()

    fun deleteRoutine(idRoutine: String) {
        repository.deleteRoutine(idRoutine)
    }
}