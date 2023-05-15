package com.example.gymroutines.data.exercise


import com.example.gymroutines.model.Exercise
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ExerciseDataSourceImpl@Inject constructor(
    private val db: FirebaseFirestore
) : ExerciseDataSource {

    /**
     * Metodo para obtener los ejercicios de la base de datos
     * @return Lista de ejercicios
     */
    override suspend fun getExercises(): Result<List<Exercise>> = runCatching {
        val result = db.collection("exercises").get().await()
        val exercises : List<Exercise> = result.toObjects(Exercise::class.java)
        exercises
    }
}