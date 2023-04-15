package com.example.gymroutines.data.chat.model


import com.example.gymroutines.model.Messages

interface ChatDataSource {
suspend fun getMessages():  Result<Messages>
}