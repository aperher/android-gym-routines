package com.example.gymroutines.data.profile


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.gymroutines.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseFirestore) : ProfileDataSource {
    /**
     * Método para obtener el usuario actual
     * @return El usuario actual
     */
    override suspend fun getUser() : Result<User> = runCatching{
        val result = db.collection("users").document(auth.currentUser?.email!!).get().await()
        result.toObject(User::class.java)!!
    }

    /**
     * Método para actualizar los datos del usuario actual
     * @return el usuario con los datos modificados
     */
    override suspend fun updateUserName(userName: String, currentUser: User): Result<Boolean> {
        //Here we update the userName by the given user
        var result = Result.success(true)
        val collection = db.collection("users")
        //val document = db.document(currentUser.email)
        val query = collection.whereEqualTo("username",currentUser.username)
        query.get().addOnSuccessListener { documents ->
            for(document in documents){
                val documentRef = collection.document(document.id)
                documentRef.update("username", userName).addOnSuccessListener {
                    result = Result.success(true)
                }.addOnFailureListener{ e ->
                    result = Result.failure(e)
                    Log.d("Exception", e.toString())
                }
            }
        }
        return result
    }
}