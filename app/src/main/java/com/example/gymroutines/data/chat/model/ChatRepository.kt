package com.example.gymroutines.data.chat.model

import com.example.gymroutines.model.Messages

interface ChatRepository {
    suspend fun getMessages(): Result<Messages>

}