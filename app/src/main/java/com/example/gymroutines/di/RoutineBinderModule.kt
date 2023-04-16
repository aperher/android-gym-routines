package com.example.gymroutines.di


import com.example.gymroutines.data.chat.ChatDataSource
import com.example.gymroutines.data.chat.ChatDataSourceImpl
import com.example.gymroutines.data.chat.ChatRepository
import com.example.gymroutines.data.chat.ChatRepositoryImpl


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RoutineBinderModule {
    @Binds
    abstract fun bindChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository

    @Binds
    abstract fun bindChatDataSource(chatDataSource: ChatDataSourceImpl): ChatDataSource
}