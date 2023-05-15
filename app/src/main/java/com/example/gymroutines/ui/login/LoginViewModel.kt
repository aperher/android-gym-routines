package com.example.gymroutines.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import com.example.gymroutines.data.auth.AuthRepository
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

    /**
     * Setea el email.
     * @param email el valor del email.
     */
    fun setEmail(email: String) {
        _email.value = email
    }

    /**
     * Setea la contraseña.
     * @param password valor de la contraseña.
     */
    fun setPassword(password: String) {
        _password.value = password
    }

    /**
     * Meotodo que Loguea al usuario.
     */
    fun login() {
        viewModelScope.launch {
            val logged : Boolean = authRepository.login(_email.value!!, _password.value!!)
            if (logged) {
                _goToHome.value = Event(true)
            }
            setPassword("")
        }
    }
}