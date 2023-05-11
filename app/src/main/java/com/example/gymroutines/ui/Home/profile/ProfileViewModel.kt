package com.example.gymroutines.ui.Home.profile

import android.util.Log
import android.widget.Toast
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
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository, private val auth: AuthRepository) : ViewModel() {
    //Get user data to update UI
    private var _currentuser = MutableLiveData<User>()
    val user: LiveData<User> get() = _currentuser
    private var _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password
    private var _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> get() = _repeatPassword


    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            repository.getUser().fold(onSuccess = {
                _currentuser.value = it
            }, onFailure = {
                TODO()
            })
        }
    }

    fun updateUserData(username: String) : Boolean{
        var result = false
        if(user.value != null && username != null){
            viewModelScope.launch{
                repository.updateUserName(username,user.value!!).fold(onSuccess = {
                    result = true
                }, onFailure = {
                    result = false
                })
            }
        }
        return result
    }

    fun updatedUserName() : String{
        if(user.value != null){
            return user.value!!.username
        }
        return ""
    }

    fun closeSession(){
        auth.logout()
    }
}