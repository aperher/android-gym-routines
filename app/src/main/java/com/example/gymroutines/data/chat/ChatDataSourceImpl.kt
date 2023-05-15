package com.example.gymroutines.data.chat


import com.example.gymroutines.data.chat.model.MessagesDto
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.model.Messages
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import java.util.*


class ChatDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val profile: ProfileRepository
) : ChatDataSource {
    var userName = ""
    var imageUrl = ""

    /**
     * Método para obtener todos mensajes de BD
     * @return Lista de mensajes
     */
    override fun getMessages(): Flow<MessagesDto> = callbackFlow {
        val query = db.collection("messages").document("messages")
        val registration = query.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                return@addSnapshotListener
            }
            val messages = snapshot.toObject(MessagesDto::class.java)
            if (messages != null) {
                trySend(messages)
            }
        }
        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)

    /**
     * Método para crear un mensaje en BD
     * @return Booleano indicando si se ha creado correctamente
     */
    override fun createMessage(text: String): Boolean = runCatching {
        val id = randomString(20)
        val message = Messages(userName, text, id, imageUrl)
        db.collection("messages").document("messages")
            .update("messages", FieldValue.arrayUnion(message))
    }.isSuccess

    private fun randomString(longitud: Int): String {
        val caracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        val sb = StringBuilder(longitud)

        for (i in 0 until longitud) {
            val indice = random.nextInt(caracteresPermitidos.length)
            sb.append(caracteresPermitidos[indice])
        }
        return sb.toString()
    }

    init {
        runBlocking() {
            userName = profile.getUserName()
            imageUrl = profile.getImageUrl()
        }
    }
}
