package com.example.gymroutines.data.routinesCatalog.model

import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.CatalogType

fun CatalogDto.toDomain() = Catalog(
    id = userId + title,
    title = CatalogType.values().find { it.value == title } ?: CatalogType.Created,
    routinesPreview = routinesPreview.map {
        it.toDomain()
    }
)