package com.example.gymroutines.data.chat.model


import com.example.gymroutines.model.Messages
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(private val datasource: ChatDataSource): ChatRepository {
    override suspend fun getMessages(): Result<Messages> {
        return datasource.getMessages();
    }
}