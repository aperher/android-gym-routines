package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.toDomain
import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.CatalogType
import com.example.gymroutines.model.RoutinePreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoutinesCatalogRepositoryImpl @Inject constructor(private val dataSource: RoutinesCatalogDataSource) :
    RoutinesCatalogRepository {

    override fun getRoutinesCatalog(): Flow<List<Catalog>> {
        return dataSource.getRoutinesCatalog().map { catalogList ->
            catalogList.map { catalogDto ->
                catalogDto.toDomain()
            }
        }
    }

    override suspend fun getRoutinesByCatalog(catalogTitle: CatalogType): Result<List<RoutinePreview>> = runCatching {
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
}