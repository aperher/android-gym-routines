package com.example.gymroutines.data.chat.model

import com.example.gymroutines.model.Messages

fun MessagesDto.toDomainList() = messages.map {
    Messages(
        id = it.id,
        userName = it.userName,
        text = it.text
    )
}
