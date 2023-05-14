package com.example.gymroutines.data.exercise

import android.util.Log
import com.example.gymroutines.model.Equipment
import com.example.gymroutines.model.Exercise
import com.example.gymroutines.model.Muscles
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ExerciseDataSourceImpl@Inject constructor(
    private val db: FirebaseFirestore
) : ExerciseDataSource {
    override suspend fun getExercises(): Result<List<Exercise>> = runCatching {
        var result = db.collection("exercises").get().await()
        var exercises : List<Exercise> = result.toObjects(Exercise::class.java)
        exercises
    }
}