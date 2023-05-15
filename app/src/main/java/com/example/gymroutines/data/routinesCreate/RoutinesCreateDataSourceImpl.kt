package com.example.gymroutines.data.routinesCreate


import com.example.gymroutines.model.Routine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RoutinesCreateDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth

) : RoutinesCreateDataSource {
    private val COLLECTION_ROUTINES = "routines"
    private val COLLECTION_CATALOG = "routinesCatalogs"

    /**
     * Método para crear un rutina en BD en la colleción de las rutinas y añadirla a los catálogos necesarios
     * @param routine La rutina a crear.
     * return true si se ha creado correctamente, false en caso contrario.
     */
    override suspend fun createRoutine(routine: Routine): Boolean = runCatching {
        val userId = firebaseAuth.currentUser?.email!!
        routine.userId = userId
        firestore.collection(COLLECTION_ROUTINES).add(routine).addOnSuccessListener {
            val nuevoObjeto = HashMap<String, Any>()
            nuevoObjeto["id"] = it
            nuevoObjeto["imageURL"] = routine.imageURL
            nuevoObjeto["title"] = routine.title


            firestore.collection(COLLECTION_CATALOG).get().addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        if (document.get("userId") == routine.userId) {
                            document.reference.update(
                                "routinesPreview", FieldValue.arrayUnion(nuevoObjeto)
                            ).addOnSuccessListener {
                                    println("Documento catalogo personal actualizado")
                                }.addOnFailureListener { exception ->
                                    println("Error al actualizar el documento catalogo personal: $exception")
                                }
                        }
                    }
                    if (routine.isPublic) {
                        firestore.collection(COLLECTION_CATALOG).document(
                            "Qsb7DsGVTBuaaBXbQ1ET"
                        ).update("routinesPreview", FieldValue.arrayUnion(nuevoObjeto))
                            .addOnSuccessListener {
                                println("Documento catalogo publico actualizado")
                            }
                    }
                }
        }.addOnFailureListener {
            println("Error al crear la rutina: $it")
        }.await()
    }.isSuccess
}
