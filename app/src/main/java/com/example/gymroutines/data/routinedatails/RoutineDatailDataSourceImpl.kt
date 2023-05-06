package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RoutineDatailDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : RoutineDetailDataSource {
    override fun deleteRoutine(idRoutine: String) {
        firestore.collection("exercises").document(idRoutine).delete()
    }

    override fun getRoutine(idRoutine: String): RoutineDetail {
        TODO("Not yet implemented")
    }
}