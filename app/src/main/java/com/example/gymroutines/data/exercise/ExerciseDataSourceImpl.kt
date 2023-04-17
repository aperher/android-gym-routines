package com.example.gymroutines.data.exercise

import com.example.gymroutines.model.Exercise
import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ExerciseDataSourceImpl@Inject constructor(
    private val db: FirebaseFirestore
) : ExerciseDataSource {
    override suspend fun getExercises(muscle: String?, equipment: String?): Result<List<Exercise>> = runCatching {
        val result = db.collection("exercises").get().await()
        result.toObjects(Exercise::class.java)!!
    }
}