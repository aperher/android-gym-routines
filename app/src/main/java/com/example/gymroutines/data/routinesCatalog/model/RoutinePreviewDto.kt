package com.example.gymroutines.data.routinesCatalog.model

import com.google.firebase.firestore.DocumentReference

data class RoutinePreviewDto(
    var id: DocumentReference? = null,
    val title: String = "",
    val durationMin: Int = 0,
    val level: String = "",
    val imageURL: String = ""
)