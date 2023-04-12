package com.example.gymroutines.model

data class Messages(val exercises: List<aMessages>) {
    data class aMessages(val userName: String, val textMessage: String)
}
