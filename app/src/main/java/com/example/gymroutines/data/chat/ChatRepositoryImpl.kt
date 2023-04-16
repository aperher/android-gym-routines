package com.example.gymroutines.data.chat


import com.example.gymroutines.data.chat.model.toDomainList
import com.example.gymroutines.model.Messages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(private val datasource: ChatDataSource): ChatRepository {
    override  fun getMessages(): Flow<List<Messages>> {
        return datasource.getMessages().map {
            it.toDomainList()
        }
    }

    override fun createMessage(text: String): Boolean {
        return datasource.createMessage(text)
    }
}