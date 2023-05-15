package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.toDomain
import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.CatalogType
import com.example.gymroutines.model.FilterType
import com.example.gymroutines.model.RoutinePreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoutinesCatalogRepositoryImpl @Inject constructor(private val dataSource: RoutinesCatalogDataSource) :
    RoutinesCatalogRepository {
    /**
     * Metodo para obtener las rutinas del catalogo de la base de datos
     * @return Lista de catalogos
     */
    override fun getRoutinesCatalog(): Flow<List<Catalog>> {
        return dataSource.getRoutinesCatalog().map { catalogList ->
            catalogList.map { catalogDto ->
                catalogDto.toDomain()
            }
        }
    }

    /**
     * Método para obtener las rutinas de un catalogo de la base de datos
     * @param catalogTitle Titulo del catalogo
     * @return Lista de rutinas
     */
    override suspend fun getRoutinesByCatalog(catalogTitle: CatalogType): Result<List<RoutinePreview>> =
        runCatching {
            dataSource.getRoutinesByCatalog(catalogTitle).let { result ->
                return if (result.isSuccess) {
                    Result.success(result.getOrThrow().map { routinePreviewDto ->
                        routinePreviewDto.toDomain()
                    })
                } else {
                    Result.failure(Exception("Error getting routines by catalog"))
                }
            }
        }

    /**
     * Método para obtener rutinas en función de los filtros
     * @param filters Filtros a aplicar
     * @return Lista de rutinas filtradas
     */
    override suspend fun getFilteredRoutines(filters: Map<FilterType, MutableList<String>>): Result<List<RoutinePreview>> =
        dataSource.getFilteredRoutines(filters).let { result ->
            return if (result.isSuccess) {
                Result.success(result.getOrThrow().map { routinePreviewDto ->
                    routinePreviewDto.toDomain()
                })
            } else {
                Result.failure(Exception("Error getting filtered routines"))
            }
        }

    /**
     * Método para acceder obtener las rutinas en funcion de el nombre
     * @param title Titulo de la rutina
     * @return Lista de rutinas
     */
    override suspend fun searchRoutines(title: String): Result<List<RoutinePreview>> {
        dataSource.searchRoutines(title).let { result ->
            return if (result.isSuccess) {
                Result.success(result.getOrThrow().map { routinePreviewDto ->
                    routinePreviewDto.toDomain()
                })
            } else {
                Result.failure(Exception("Error searching routines"))
            }
        }
    }
}