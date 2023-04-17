package com.example.gymroutines.data.chat

import com.example.gymroutines.model.Messages
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    fun getMessages(): Flow<List<Messages>>
    fun createMessage(text: String): Boolean
}