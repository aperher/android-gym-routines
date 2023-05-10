package com.example.gymroutines.data.routinesCatalog.model

import com.google.firebase.firestore.DocumentReference

data class RoutinePreviewDto(var id: DocumentReference? = null, val title: String = "", val imageURL: String = "")