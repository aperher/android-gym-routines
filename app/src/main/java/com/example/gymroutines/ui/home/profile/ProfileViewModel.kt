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

    fun getUser() {
        viewModelScope.launch {
            repository.getUser().fold(onSuccess = {
                _currentuser.value = it
            }, onFailure = {
                exception.value = it
            })
        }
    }

    fun updatedUserName(): String {
        if (user.value != null) {
            return user.value!!.username
        }
        return ""
    }

    fun closeSession() {
        auth.logout()
    }

    fun resetError() {
        exception.value = null
    }
}