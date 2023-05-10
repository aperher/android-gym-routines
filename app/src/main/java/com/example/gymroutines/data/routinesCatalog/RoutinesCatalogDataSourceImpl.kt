package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.CatalogDto
import com.example.gymroutines.data.routinesCatalog.model.RoutinePreviewDto
import com.example.gymroutines.model.CatalogType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
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

    private companion object {
        const val COLLECTION_ROUTINES = "routines"
        const val COLLECTION_CATALOGS = "routinesCatalogs"
        const val FIELD_PRIORITY_ORDER = "priorityOrder"
        const val FIELD_USER_ID = "userId"
        const val FIELD_TITLE = "title"
        const val FIELD_ROUTINES_PREVIEW = "routinesPreview"
        const val FIELD_IMAGE_URL = "imageURL"
        const val ALL = "all"
    }

    override fun getRoutinesCatalog(): Flow<List<CatalogDto>> = callbackFlow {
        val query = fireStore.collection(COLLECTION_CATALOGS)
            .whereIn(FIELD_USER_ID, listOf(userId, ALL))
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

    override suspend fun getRoutinesByCatalog(catalogTitle: CatalogType): Result<List<RoutinePreviewDto>> =
        runCatching {
            val query: Query = when (catalogTitle) {
                CatalogType.Created, CatalogType.Favourite -> fireStore.collection(COLLECTION_CATALOGS)
                    .whereEqualTo(FIELD_TITLE, catalogTitle.value).whereEqualTo(FIELD_USER_ID, userId)
                CatalogType.Popular -> fireStore.collection(COLLECTION_ROUTINES)
                CatalogType.Community -> fireStore.collection(COLLECTION_ROUTINES)
            }

            val snapshot = query.get().await()

            val routines = mutableListOf<RoutinePreviewDto>()
            if (catalogTitle != CatalogType.Created && catalogTitle != CatalogType.Favourite) {
                for (document in snapshot.documents) {
                    val routine = document.toObject(RoutinePreviewDto::class.java)
                    routine?.id = document.reference
                    routines.add(routine!!)
                }
            } else {
                snapshot.documents[0].data?.get(FIELD_ROUTINES_PREVIEW)?.let {
                    val routinesPreview = it as List<Map<String, Any>>
                    for (routinePreview in routinesPreview) {
                        val routine = RoutinePreviewDto(
                            id = routinePreview["id"] as DocumentReference,
                            title = routinePreview[FIELD_TITLE] as String,
                            imageURL = routinePreview[FIELD_IMAGE_URL] as String
                        )
                        routines.add(routine)
                    }
                }
            }

            routines
        }
}