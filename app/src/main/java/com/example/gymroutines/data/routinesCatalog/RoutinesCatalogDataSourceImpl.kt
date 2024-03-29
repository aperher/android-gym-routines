package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.Favorites
import com.example.gymroutines.data.routinesCatalog.model.CatalogDto
import com.example.gymroutines.data.routinesCatalog.model.RoutinePreviewDto
import com.example.gymroutines.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
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

    /**
     * Metodo para obtener las rutinas del catalogo de la base de datos
     * @return Lista de catalogos
     */
    override fun getRoutinesCatalog(): Flow<List<CatalogDto>> = callbackFlow {
        val query = fireStore.collection(COLLECTION_CATALOGS)
            .whereIn(FIELD_USER_ID, listOf(userId, ALL))
            .orderBy(FIELD_PRIORITY_ORDER)

        val registration = query.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                return@addSnapshotListener
            }

            Favorites.documentId = snapshot.documents[1].id
            Favorites.favouriteRoutines =
                snapshot.documents[1].get(FIELD_FAVOURITES) as MutableList<String>

            val catalogs = snapshot.toObjects(CatalogDto::class.java)
            trySend(catalogs)
        }

        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)

    /**
     * Método para obtener las rutinas de un catalogo de la base de datos
     * @param catalogTitle Titulo del catalogo
     * @return Lista de rutinas
     */
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

    /**
     * Método para obtener rutinas en función de los filtros
     * @param filters Filtros a aplicar
     * @return Lista de rutinas filtradas
     */
    override suspend fun getFilteredRoutines(filters: Map<FilterType, MutableList<String>>): Result<List<RoutinePreviewDto>> =
        runCatching {
            // Realizar filtrado por muscles equipment y level
            val baseQuery: Query = fireStore.collection(COLLECTION_ROUTINES)
            var query: Query = baseQuery

            if (!filters[FilterType.Equipment].isNullOrEmpty()) {
                val equipmentList = filters[FilterType.Equipment]!!.map { element ->
                    Equipment.values().find { it.value == element }.toString()
                }
                query = query.whereArrayContainsAny(
                    FilterType.Equipment.toString().lowercase(),
                    equipmentList
                )
            }
            if (!filters[FilterType.Muscles].isNullOrEmpty()) {
                val musclesList = filters[FilterType.Muscles]!!.map { element ->
                    Muscles.values().find { it.value == element }.toString()
                }
                query = query.whereArrayContainsAny(
                    FilterType.Muscles.toString().lowercase(),
                    musclesList
                )
            }
            if (!filters[FilterType.Level].isNullOrEmpty()) {
                val levelList = filters[FilterType.Level]!!.map { element ->
                    Level.values().find { it.value == element }.toString()
                }
                query = query.whereIn(FilterType.Level.toString().lowercase(), levelList)
            }
            val routines = query.get().await().documents.map {
                it.toObject(RoutinePreviewDto::class.java)!!.apply { id = it.reference }
            }
            routines
        }

    /**
     * Metodo para buscar rutinas a partir del buscador
     * @param title Titulo de la rutina
     * @return Lista de rutinas
     */
    override suspend fun searchRoutines(title: String): Result<List<RoutinePreviewDto>> =
        runCatching {
            fireStore.collection(COLLECTION_ROUTINES).whereEqualTo(FIELD_TITLE, title).get().await()
                .toObjects(RoutinePreviewDto::class.java)
        }
}