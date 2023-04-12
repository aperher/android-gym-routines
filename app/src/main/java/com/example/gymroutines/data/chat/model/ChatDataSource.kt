package com.example.gymroutines.data.chat.model


import com.example.gymroutines.model.Messages
import kotlinx.coroutines.flow.Flow


interface ChatDataSource {
suspend fun getMessages():  Result<Messages>
}