package com.example.gymroutines.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymroutines.utils.Event
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {
    private var _goToHome = MutableLiveData<Event<Boolean>>()
    val goToHome: MutableLiveData<Event<Boolean>> get() = _goToHome

    init {
        checkIfUserIsLoggedIn()
    }

    private fun checkIfUserIsLoggedIn() {
        if (auth.currentUser != null) _goToHome.value = Event(true)
    }
}