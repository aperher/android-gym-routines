package com.example.gymroutines.ui.Login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.*
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.model.User
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private var _email = MutableLiveData<String>("")
    private var _password = MutableLiveData<String>("")

    val invalidEmailError: LiveData<String?> = _email.map {
        if (Patterns.EMAIL_ADDRESS.matcher(it).matches() || it.isEmpty()) null else "Invalid email"
    }

    val invalidPasswordError: LiveData<String?> = _password.map {
        if (it.length >= 6 || it.isEmpty()) null else "Invalid password"
    }

    private var _goToHome = MutableLiveData<Event<Boolean>>()
    val goToHome: LiveData<Event<Boolean>> get() = _goToHome

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        viewModelScope.launch {
            authRepository.login(_email.value!!, _password.value!!).fold(
                onSuccess = { _goToHome.value = Event(true) },
                onFailure = { TODO("Not yet implemented") }
            )
            setPassword("")
        }
    }
}