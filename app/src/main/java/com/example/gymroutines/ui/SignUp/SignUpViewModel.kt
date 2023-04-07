package com.example.gymroutines.ui.SignUp

import android.util.Patterns
import androidx.lifecycle.*
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.model.User
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private var _username = MutableLiveData<String>("")
    private var _email = MutableLiveData<String>("")
    private var _password = MutableLiveData<String>("")
    private var _confirmPassword = MutableLiveData<String>("")

    val invalidEmailError: LiveData<String?> = _email.map {
        if (Patterns.EMAIL_ADDRESS.matcher(it).matches() || it.isEmpty()) null else "Invalid email"
    }

    val invalidPasswordError: LiveData<String?> = _password.map {
        if (it.length >= 6 || it.isEmpty()) null else "Invalid password"
    }

    val invalidConfirmPasswordError: LiveData<String?> = _confirmPassword.map {
        if (it == _password.value || it.isEmpty()) null else "Enter the same password"
    }

    private var _goToHome = MutableLiveData<Event<Boolean>>()
    val goToHome: LiveData<Event<Boolean>> get() = _goToHome

    fun setName(username: String) {
        _username.value = username
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmPassword(password: String) {
        _confirmPassword.value = password
    }

    fun signUp() {
        viewModelScope.launch {
            val registered: Boolean =
                authRepository.register(User(_username.value!!, _email.value!!, _password.value!!))
            if (registered) {
                _goToHome.value = Event(true)
            }
            setPassword("")
            setConfirmPassword("")
        }
    }
}