package com.example.gymroutines.data.chat.model

import com.example.gymroutines.model.Messages

data class MessagesDto(val messages: List<messageDto> = emptyList())  {
    data class messageDto(val userName: String = "", val text: String = "", var id: String = "")
}
