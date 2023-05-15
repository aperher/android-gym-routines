package com.example.gymroutines.data.profile

import com.example.gymroutines.model.User
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(val datasource: ProfileDataSource) :
    ProfileRepository {
    /**
     * Metodo que accede al datasource para obtener el usuario
     * @return El usuario
     */
    override suspend fun getUser(): Result<User> {
        return datasource.getUser()
    }

    /**
     * Metodo para obtener el nombre de usuario
     * @return El nombre de usuario
     */
    override suspend fun getUserName(): String {
        var Username =""
        runBlocking() {
            getUser().fold(onSuccess = {
                Username =  it.username
            }, onFailure = {
                println(it.toString())
            })

        }
        return Username;
    }

    /**
     * Metodo para obtener la foto de perfil del usuario
     * @return La url de la foto de perfil
     */
    override suspend fun getImageUrl(): String {
        var ImageUrl =""
        runBlocking() {
            getUser().fold(onSuccess = {
                ImageUrl =  it.imageUrl
            }, onFailure = {
                println(it.toString())
            })

        }
        return ImageUrl;
    }

    /**
     * Metodo que accede al datasource para actualizar el nombre de usuario
     * @param userName El nuevo nombre de usuario
     */
    override suspend fun updateUserName(userName: String, currentUser: User): Result<Boolean> {
        return datasource.updateUserName(userName, currentUser)
    }
}