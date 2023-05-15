package com.example.gymroutines.data.routinesCreate



import com.example.gymroutines.model.Routine
import javax.inject.Inject

class RoutinesCreateRepositoryImpl @Inject constructor(
    private val routinesCreateDataSource: RoutinesCreateDataSource
) : RoutinesCreateRepository {

    /**
     * Metodo que accede al datasource para crear una rutina.
     * @param routine La rutina a crear.
     * @return `true` si la rutina se creó correctamente, `false` en caso contrario.
     */
    override suspend fun createRoutine(routine: Routine): Boolean {
        return routinesCreateDataSource.createRoutine(routine)
    }
}