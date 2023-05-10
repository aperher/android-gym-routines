package com.example.gymroutines.data.routinesCatalog.model

import com.example.gymroutines.model.Level
import com.example.gymroutines.model.RoutinePreview

fun RoutinePreviewDto.toDomain() = RoutinePreview(
    id = id?.id,
    title = title,
    information = "$durationMin min  •  ${
        try {
            Level.valueOf(level).value
        } catch (e: Exception) {
            "Level está mal escrito en bd (Easy, Intermediate o Advanced)"
        }
    }",
    imageURL = imageURL
)