package com.example.gymroutines.data.chat


import com.example.gymroutines.data.chat.model.MessagesDto

import kotlinx.coroutines.flow.Flow


interface ChatDataSource {

 fun getMessages():  Flow<MessagesDto>

 fun createMessage(text: String): Boolean
}