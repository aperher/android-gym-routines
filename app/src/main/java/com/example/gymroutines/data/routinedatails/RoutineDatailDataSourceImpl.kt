package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.data.chat.model.MessagesDto
import com.example.gymroutines.model.RoutineDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RoutineDatailDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : RoutineDetailDataSource {
    override fun deleteRoutine(idRoutine: String) {
        firestore.collection("exercises").document(idRoutine).delete()
    }

    override fun getRoutine(idRoutine: String): Flow<RoutineDetail> = callbackFlow {
        val query = firestore.collection("exercises").document(idRoutine)
        val registration = query.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                return@addSnapshotListener
            }
            val routine = snapshot.toObject(RoutineDetail::class.java)
            if (routine != null) {
                trySend(routine)
            }
        }
        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)

}
