package com.example.gymroutines.ui.Home.profile

import android.widget.Toast
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
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {
    //Get user data to update UI
    private var _currentuser = MutableLiveData<User>()
    val user: LiveData<User> get() = _currentuser

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

    fun updateUserData(username: String?, actualPassword: String?, newPassword: String?, repeatNewPassword: String?){
        if((username != null && actualPassword != null && newPassword != null && repeatNewPassword != null) && username != user.value?.username){
            //Actualizar nombre de usuario y contraseña si es posible updateAllUserData
            if(actualPassword.equals(user.value?.password) && (newPassword.equals(repeatNewPassword))){
                /*viewModelScope.launch{
                    repository.updateAllUserData(username,newPassword).fold(onSuccess = {
                        Toast.makeText(null,"Data sucessfully updated", Toast.LENGTH_SHORT).show()
                    }, onFailure = {
                        TODO()
                    })
                }*/
                Toast.makeText(null,"Data sucessfully updated", Toast.LENGTH_SHORT).show()
            }
            if(!actualPassword.equals(user.value?.password)){
                Toast.makeText(null,"Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
            if(!newPassword.equals(repeatNewPassword)){
                Toast.makeText(null,"Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
        else if(username != null){
            //Actualizar unicamente el nombre de usuario updateUserName
            /*viewModelScope.launch {
                repository.updateUserName(username).fold(onSuccess = {

                }, onFailure = {
                    TODO()
                })
            }*/
            Toast.makeText(null,"Nombre de usuario cambiado", Toast.LENGTH_SHORT).show()
        }
        else if(actualPassword != null && newPassword != null && repeatNewPassword != null){
            //Actualizar unicamente la contraseña si es posible updateUserPassword
            /*viewModelScope.launch{
                repository.updateUserPassword(newPassword).fold(onSuccess = {

                }, onFailure = {
                    TODO()
                })
            }*/
            Toast.makeText(null,"Contraseña cambiada", Toast.LENGTH_SHORT).show()
        }
    }
}