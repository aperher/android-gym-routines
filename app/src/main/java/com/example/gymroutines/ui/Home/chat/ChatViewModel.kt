package com.example.gymroutines.ui.Home.chat

import androidx.lifecycle.*
import com.example.gymroutines.data.chat.ChatRepository
import com.example.gymroutines.model.Messages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {
    private var _textMessage = MutableLiveData<String>("")
    val listMessages: LiveData<List<Messages>> =
        repository.getMessages().asLiveData()

    private val _isTextEmpty = MutableLiveData(false);
    val isTextEmpty: LiveData<Boolean>
    get() = _isTextEmpty

    fun setTextMessage(text: String) {
        _textMessage.value = text
        _isTextEmpty.value = text.isNullOrEmpty()
    }

    fun createMessage() {
        repository.createMessage(_textMessage.value!!)
    }
}

