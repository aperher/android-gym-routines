package com.example.gymroutines.data.exercise

import android.util.Log
import com.example.gymroutines.model.Exercise
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ExerciseDataSourceImpl@Inject constructor(
    private val db: FirebaseFirestore
) : ExerciseDataSource {
    override suspend fun getExercises(muscle: String?, equipment: String?): Result<List<Exercise>> = runCatching {
        var result: QuerySnapshot
        if (muscle == null && equipment == null) {
            result = db.collection("exercises").get().await()
        } else if (muscle == null) {
            result = db.collection("exercises").whereEqualTo("equipment", equipment).get().await()
        } else if (equipment == null) {
            result = db.collection("exercises").whereEqualTo("primaryMuscles", muscle).get().await()
        } else {
            result = db.collection("exercises").whereEqualTo("equipment", equipment).get().await()
        }
        Log.d("patata", result.toObjects(Exercise::class.java).toString())
        var exercises : List<Exercise> = result.toObjects(Exercise::class.java)
        exercises
    }
}