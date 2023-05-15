package com.example.gymroutines.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val auth: AuthRepository
) : ViewModel() {
    //Get user data to update UI
    private var _currentuser = MutableLiveData<User>()
    val user: LiveData<User> get() = _currentuser
    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception

    init {
        getUser()
    }

    /**
     * Obtiene los datos del usuario desde el repositorio de perfil.
     * En caso de éxito, actualiza el valor de _currentuser.
     * En caso de error, establece el valor de _exception.
     */
    fun getUser() {
        viewModelScope.launch {
            repository.getUser().fold(onSuccess = {
                _currentuser.value = it
            }, onFailure = {
                exception.value = it
            })
        }
    }

    /**
     * Devuelve el nombre de usuario.
     * @return El nombre de usuario, o una cadena vacía si el valor de user es nulo.
     */
    fun updatedUserName(): String {
        if (user.value != null) {
            return user.value!!.username
        }
        return ""
    }

    /**
     * Método para cerrar sesion del usuario
     */
    fun closeSession() {
        auth.logout()
    }

    /**
     * Restablece el valor de _exception a null.
     */
    fun resetError() {
        exception.value = null
    }
}