package com.example.gymroutines.data.routineDetails

import com.example.gymroutines.data.Favorites
import com.example.gymroutines.model.RoutineDetail
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RoutineDetailDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : RoutineDetailDataSource {

    private val COLLECTION_ROUTINES = "routines"
    private val COLLECTION_CATALOG = "routinesCatalogs"

    /**
     * Método para añadir una rutina de favoritos
     * @param idRoutine El id de la rutina a a añadir en favoritos.
     */
    override fun addFavourite(idRoutine: String) {
        fireStore.collection(COLLECTION_CATALOG).document(Favorites.documentId)
            .update("favourites", FieldValue.arrayUnion(idRoutine))
        if (!Favorites.favouriteRoutines.contains(idRoutine)) Favorites.favouriteRoutines.add(
            idRoutine
        )
    }

    /**
     * Método para borrar una rutina de favoritos
     * @param idRoutine El id de la rutina a borrar de favoritos.
     */
    override fun removeFavourite(idRoutine: String) {
        fireStore.collection(COLLECTION_CATALOG).document(Favorites.documentId)
            .update("favourites", FieldValue.arrayRemove(idRoutine))
        if (Favorites.favouriteRoutines.contains(idRoutine)) Favorites.favouriteRoutines.remove(
            idRoutine
        )
    }

    /**
     * Metodo para borrar una rutina de BD en la colleción de las rutinas y eliminarla de los catálogos necesarios
     * @param idRoutine El id de la rutina a borrar.
     */
    override fun deleteRoutine(idRoutine: String) {
        fireStore.collection(COLLECTION_ROUTINES).document(idRoutine).delete()
    }

    /**
     * Método para obtener una rutina de la BD en funcion de su ID
     * @param idRoutine El id de la rutina a obtener.
     * return Flow<RoutineDetail> con la rutina obtenida.
     */
    override fun getRoutine(idRoutine: String): Flow<RoutineDetail> = callbackFlow {
        val query = fireStore.collection(COLLECTION_ROUTINES).document(idRoutine)
        val registration = query.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                return@addSnapshotListener
            }
            val routine = snapshot.toObject(RoutineDetail::class.java)
            val routineId = snapshot.id
            routine?.isFavourite = Favorites.favouriteRoutines.contains(routineId)

            if (routine != null) {
                trySend(routine)
            }
        }
        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)

}
