package com.example.gymroutines.di

import com.example.gymroutines.data.chat.model.ChatDataSource
import com.example.gymroutines.data.chat.model.ChatDataSourceImpl
import com.example.gymroutines.data.chat.model.ChatRepository
import com.example.gymroutines.data.chat.model.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChatBinderModule {
    @Binds
    abstract fun bindChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository

    @Binds
    abstract fun bindChatDataSource(chatDataSource: ChatDataSourceImpl): ChatDataSource
}