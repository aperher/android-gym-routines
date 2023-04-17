package com.example.gymroutines.ui.Home.profile.editProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val repository: ProfileRepository): ViewModel() {
    private var _currentuser = MutableLiveData<User>()
    val user: LiveData<User> get() = _currentuser
    private var _username = MutableLiveData<String>("Carlos")
    val username: LiveData<String> get() = _username
    private var _useremail = MutableLiveData<String>("email")
    val useremail: LiveData<String> get() = _useremail
    init {
        /*
        viewModelScope.launch {
            repository.getUser().fold(onSuccess = {
                _currentuser.value = it
            }, onFailure = {
                TODO()
            })
        }*/
    }
}