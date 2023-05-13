package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.Favorites
import com.example.gymroutines.data.routinesCatalog.model.CatalogDto
import com.example.gymroutines.data.routinesCatalog.model.RoutinePreviewDto
import com.example.gymroutines.model.CatalogType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RoutinesCatalogDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    firebaseAuth: FirebaseAuth
) : RoutinesCatalogDataSource {

    private val userId = firebaseAuth.currentUser?.email

    private val COLLECTION_ROUTINES = "routines"
    private val COLLECTION_CATALOGS = "routinesCatalogs"
    private val FIELD_PRIORITY_ORDER = "priorityOrder"
    private val FIELD_USER_ID = "userId"
    private val FIELD_TITLE = "title"
    private val FIELD_POPULARITY = "popularity"
    private val FIELD_FAVOURITES = "favourites"
    private val ALL = "all"

    override fun getRoutinesCatalog(): Flow<List<CatalogDto>> = callbackFlow {
        val query = fireStore.collection(COLLECTION_CATALOGS)
            .whereIn(FIELD_USER_ID, listOf(userId, ALL))
            .orderBy(FIELD_PRIORITY_ORDER)

        val registration = query.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                return@addSnapshotListener
            }

            Favorites.documentId = snapshot.documents[1].id
            Favorites.favouriteRoutines = snapshot.documents[1].get(FIELD_FAVOURITES) as MutableList<String>

            val catalogs = snapshot.toObjects(CatalogDto::class.java)
            trySend(catalogs)
        }

        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRoutinesByCatalog(catalogTitle: CatalogType): Result<List<RoutinePreviewDto>> =
        runCatching {
            val query: Query = when (catalogTitle) {
                CatalogType.Created -> fireStore.collection(COLLECTION_ROUTINES)
                    .whereEqualTo(FIELD_USER_ID, userId)
                CatalogType.Favourite -> fireStore.collection(COLLECTION_ROUTINES)
                    .whereIn(FieldPath.documentId(), Favorites.favouriteRoutines)
                CatalogType.Popular -> fireStore.collection(COLLECTION_ROUTINES).orderBy(
                    FIELD_POPULARITY, Query.Direction.DESCENDING
                )
                CatalogType.Community -> fireStore.collection(COLLECTION_ROUTINES).orderBy(
                    FIELD_TITLE, Query.Direction.ASCENDING
                )
            }

            val snapshot = query.get().await()

            val routines = mutableListOf<RoutinePreviewDto>()
            for (document in snapshot.documents) {
                val routine = document.toObject(RoutinePreviewDto::class.java)
                routine?.id = document.reference
                routines.add(routine!!)
            }

            routines
        }
}