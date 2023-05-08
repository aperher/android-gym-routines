package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.toDomain
import com.example.gymroutines.model.Catalog
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

    override fun getRoutinesByCatalog(catalogTitle: String): Flow<List<Catalog>> {
        TODO("Not yet implemented")
    }

}