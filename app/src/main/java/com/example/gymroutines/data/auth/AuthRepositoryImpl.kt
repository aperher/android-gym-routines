package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {

    /**
     * Metodo que accede al datasource para registrar usuario en BD
     * @param user El usuario a registrar
     * @return True si se ha registrado correctamente, false en caso contrario
     */
    override suspend fun register(user: User): Boolean = dataSource.createUser(user)

    /**
     * Metodo que accede al datasource para iniciar sesion
     * @param email Correo del usuario
     * @param password Contraseña del usuario
     * @return True si se ha iniciado sesion correctamente, false en caso contrario
     */
    override suspend fun login(email: String, password: String): Boolean =
        dataSource.login(email, password)

    /**
     * Metodo que accede al datasource para cerrar sesion
     */
    override fun logout() = dataSource.logout()

    /**
     * Metodo que accede al datasource para obtener el usuario actual
     * @return El email del usuario actual
     */
    override fun getUser(): String = dataSource.getUser()
}