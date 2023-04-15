package com.example.gymroutines.data.chat


import com.example.gymroutines.data.chat.model.MessagesDto
import com.example.gymroutines.model.Messages
import kotlinx.coroutines.flow.Flow


interface ChatDataSource {

 fun getMessages():  Flow<MessagesDto>
}