package com.example.gymroutines.data.routinesCatalog.model

import com.example.gymroutines.model.RoutinePreview

fun RoutinePreviewDto.toDomain() = RoutinePreview(
    id = id?.id,
    title = title,
    imageURL = imageURL
)