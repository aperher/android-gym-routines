package com.example.gymroutines.data.routinesCatalog.model

import com.example.gymroutines.model.Catalog

fun CatalogDto.toDomain() = Catalog(
    id = userId+title,
    title = title,
    routinesPreview = routinesPreview.map {
        it.toDomain()
    }
)