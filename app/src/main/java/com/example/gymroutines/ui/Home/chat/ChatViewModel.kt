package com.example.gymroutines.ui.Home.chat


import androidx.lifecycle.*
import com.example.gymroutines.data.chat.model.ChatRepository
import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.Messages
import com.example.gymroutines.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {
    private var _messages = MutableLiveData<Messages>()
    val messages: LiveData<Messages> get() = _messages

    fun getMessages(){
        viewModelScope.launch {
            repository.getMessages().fold(onSuccess = {
                _messages.value = it
            }, onFailure = {
                TODO()
            })
        }
    }

}