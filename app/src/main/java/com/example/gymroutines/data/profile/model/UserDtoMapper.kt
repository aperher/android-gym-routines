package com.example.gymroutines.data.profile.model

import com.example.gymroutines.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain() = User(
        username=displayName!!,
        email=email!!,
    )