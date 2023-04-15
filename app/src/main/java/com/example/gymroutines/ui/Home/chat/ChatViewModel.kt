package com.example.gymroutines.ui.Home.chat


import androidx.lifecycle.*
import com.example.gymroutines.data.chat.ChatRepository
import com.example.gymroutines.model.Messages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {


    val listMessages: LiveData<List<Messages>> =
        repository.getMessages().asLiveData()


}

