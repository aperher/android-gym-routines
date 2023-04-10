package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.CatalogDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RoutinesCatalogDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : RoutinesCatalogDataSource {

    companion object {
        const val COLLECTION_CATALOGS = "routinesCatalogs"
        const val FIELD_PRIORITY_ORDER = "priorityOrder"
        const val FIELD_USER_ID = "userId"
    }

    override fun getRoutinesCatalog(): Flow<List<CatalogDto>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.email
        val query = firestore.collection(COLLECTION_CATALOGS)
            .whereIn(FIELD_USER_ID, listOf(userId, "all"))
            .orderBy(FIELD_PRIORITY_ORDER)

        val registration = query.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                return@addSnapshotListener
            }

            val catalogs = snapshot.toObjects(CatalogDto::class.java)
            trySend(catalogs)
        }

        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)
}