package com.example.gymroutines.data.routineDetails

import com.example.gymroutines.model.RoutineDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoutineDetailRepositoryImpl @Inject constructor(private val dataSource: RoutineDetailDataSource) :
    RoutineDetailRepository {
    /**
     * Accede al datasource para añadir una rutina a favoritos
     * @param idRoutine El id de la rutina a añadir en favoritos.
     */
    override fun addFavourite(idRoutine: String) {
        dataSource.addFavourite(idRoutine)
    }

    /**
     * Accede al datasource para borrar una rutina de favoritos
     * @param idRoutine El id de la rutina a borrar de favoritos.
     */
    override fun removeFavourite(idRoutine: String) {
        dataSource.removeFavourite(idRoutine)
    }

    /**
     * Accede al datasource para borrar una rutina de BD en la colleción de las rutinas y eliminarla de los catálogos necesarios
     * @param idRoutine El id de la rutina a borrar.
     */
    override fun deleteRoutine(idRoutine: String) {
        dataSource.deleteRoutine(idRoutine)
    }

    /**
     * Accede al datasource para obtener una rutina de la BD en funcion de su ID
     * @param idRoutine El id de la rutina a obtener.
     * return Flow<RoutineDetail> con la rutina obtenida.
     */
    override fun getRoutine(idRoutine: String): Flow<RoutineDetail> {
        return dataSource.getRoutine(idRoutine)
    }
}