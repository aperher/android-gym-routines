package com.example.gymroutines.ui.Home.routinesCatalog

import androidx.lifecycle.*
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogRepository
import com.example.gymroutines.model.Catalog
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutinesViewModel @Inject constructor(private val repository: RoutinesCatalogRepository): ViewModel() {
    private var _goToRoutineDetails = MutableLiveData<Event<String>>()
    val goToRoutineDetails: LiveData<Event<String>> get() = _goToRoutineDetails

    val routinesCatalog: LiveData<List<Catalog>> =
        repository.getRoutinesCatalog().asLiveData()

    val isLoading: LiveData<Boolean> = routinesCatalog.map { it.isEmpty() }

    fun onRoutineClicked(routineId: String) {
        _goToRoutineDetails.value = Event(routineId)
    }
}