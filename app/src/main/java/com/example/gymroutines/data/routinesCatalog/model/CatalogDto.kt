package com.example.gymroutines.data.routinesCatalog.model

data class CatalogDto(val priorityOrder: Int = Int.MAX_VALUE, val userId: String = "", val title: String = "", val routinesPreview : List<RoutinePreviewDto> = listOf())
