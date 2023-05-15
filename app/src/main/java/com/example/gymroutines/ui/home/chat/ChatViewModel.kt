package com.example.gymroutines.ui.home.chat

import androidx.lifecycle.*
import com.example.gymroutines.data.chat.ChatRepository
import com.example.gymroutines.model.Messages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository,
) : ViewModel() {
    private var _textMessage = MutableLiveData("")
    val listMessages: LiveData<List<Messages>> =
        repository.getMessages().asLiveData()

    private val _isTextEmpty = MutableLiveData(false)
    val isTextEmpty: LiveData<Boolean>
        get() = _isTextEmpty

    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception

    /**
     * Establece el texto del mensaje en el ViewModel y actualiza el estado de _isTextEmpty.
     * @param text El texto del mensaje.
     */
    fun setTextMessage(text: String) {
        _textMessage.value = text
        _isTextEmpty.value = text.isEmpty()
    }

    /**
     * Crea un nuevo mensaje utilizando el texto actual del mensaje en _textMessage.
     */
    fun createMessage() {
        repository.createMessage(_textMessage.value!!)
    }

    /**
     * Restablece el valor de _exception a null.
     */
    fun resetError() {
        exception.value = null
    }
}

