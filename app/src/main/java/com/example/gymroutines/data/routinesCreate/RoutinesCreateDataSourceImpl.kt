package com.example.gymroutines.data.routinesCreate


import com.example.gymroutines.model.Routine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RoutinesCreateDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : RoutinesCreateDataSource {
    override suspend fun createRoutine(routine: Routine): Boolean = runCatching {
        val userId = firebaseAuth.currentUser?.email!!
         routine.userId = userId
        firestore.collection("routines").add(routine).await()
    }.isSuccess
    }
