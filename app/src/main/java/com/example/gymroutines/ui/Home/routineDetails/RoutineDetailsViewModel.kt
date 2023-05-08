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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutineDetailsViewModel @Inject constructor(private val repository: RoutineDetailRepository) :
    ViewModel() {

    fun deleteRoutine(idRoutine: String) {
        repository.deleteRoutine(idRoutine)
    }

    fun getRoutine(idRoutine: String): LiveData<RoutineDetail> =
        repository.getRoutine(idRoutine).asLiveData()
}