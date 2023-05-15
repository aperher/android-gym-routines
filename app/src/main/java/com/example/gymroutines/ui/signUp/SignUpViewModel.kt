package com.example.gymroutines.ui.signUp

import android.util.Patterns
import androidx.lifecycle.*
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.model.User
import com.example.gymroutines.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

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

    /**
     * Establece el nombre de usuario.
     */
    fun setName(username: String) {
        _username.value = username
    }

    /**
     * Establece email del usuario.
     */
    fun setEmail(email: String) {
        _email.value = email
    }

    /**
     * Establece la contraseña del usuario.
     */
    fun setPassword(password: String) {
        _password.value = password
    }

    /**
     * Establece la confirmación de contraseña.
     */
    fun setConfirmPassword(password: String) {
        _confirmPassword.value = password
    }

    /**
     * Realiza el proceso de registro accediendo al authRepository.
     */
    fun signUp() {
        viewModelScope.launch {
            val strings = listOf("Perfil1", "Perfil2", "Perfil3", "Perfil4")
            val randomIndex = Random.nextInt(strings.size)
            val registered: Boolean =
                authRepository.register(
                    User(
                        _username.value!!,
                        _email.value!!,
                        _password.value!!,
                        strings[randomIndex]
                    )
                )
            if (registered) {
                _goToHome.value = Event(true)
            }
            setPassword("")
            setConfirmPassword("")
        }
    }
}