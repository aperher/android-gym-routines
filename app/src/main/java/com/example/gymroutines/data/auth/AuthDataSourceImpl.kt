package com.example.gymroutines.data.auth

import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthDataSource {
    companion object {
        const val USER_COLLECTION = "users"
    }

    /**
     * Método para crear un usuario en la base de datos
     * @param user Usuario a crear
     * @return True si se ha creado correctamente, false en caso contrario
     */
    override suspend fun createUser(user: User): Boolean = runCatching {
        val account = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        if (account != null)
            db.collection(USER_COLLECTION).document(user.email).set(user).await()
    }.isSuccess

    /**
     * Metodo para iniciar sesion
     * @param email Correo del usuario
     * @param password Contraseña del usuario
     * @return True si se ha iniciado sesion correctamente, false en caso contrario
     */
    override suspend fun login(email: String, password: String): Boolean = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
    }.isSuccess

    /**
     * Metodo para cerrar sesion
     */
    override fun logout() = auth.signOut()

    /**
     *Método para obtener el usuario actual
     */
    override fun getUser(): String = auth.currentUser?.email ?: ""
}