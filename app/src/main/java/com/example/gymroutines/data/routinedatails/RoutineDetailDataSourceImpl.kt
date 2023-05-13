package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.data.Favorites
import com.example.gymroutines.model.RoutineDetail
import com.google.firebase.auth.FirebaseAuth
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
    override fun addFavourite(idRoutine: String) {
        fireStore.collection(COLLECTION_CATALOG).document(Favorites.documentId).update("favourites", FieldValue.arrayUnion(idRoutine))
        if (!Favorites.favouriteRoutines.contains(idRoutine)) Favorites.favouriteRoutines.add(idRoutine)
    }

    override fun removeFavourite(idRoutine: String) {
        fireStore.collection(COLLECTION_CATALOG).document(Favorites.documentId).update("favourites", FieldValue.arrayRemove(idRoutine))
        if (Favorites.favouriteRoutines.contains(idRoutine)) Favorites.favouriteRoutines.remove(idRoutine)
    }

    override fun deleteRoutine(idRoutine: String) {
        fireStore.collection(COLLECTION_ROUTINES).document(idRoutine).delete()
    }

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
