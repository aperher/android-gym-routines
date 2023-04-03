package com.example.gymroutines.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymroutines.utils.Event
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {
    private var _goToSignIn = MutableLiveData<Event<Boolean>>()
    val goToSignIn: MutableLiveData<Event<Boolean>> get() = _goToSignIn

    init {
        checkIfUserIsLoggedIn()
    }

    private fun checkIfUserIsLoggedIn() {
        if (auth.currentUser == null) _goToSignIn.value = Event(true)
    }
}