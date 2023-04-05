package com.example.gymroutines.ui.Home.routines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutinesViewModel @Inject constructor() : ViewModel() {
    private var _goToRoutineDetails = MutableLiveData<Event<String>>()
    val goToRoutineDetails: LiveData<Event<String>> get() = _goToRoutineDetails

    fun onRoutineClicked(routineId: String) {
        _goToRoutineDetails.value = Event(routineId)
    }
}