package com.example.gymroutines.ui.Home.chat

import androidx.lifecycle.*
import com.example.gymroutines.data.chat.ChatRepository
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.model.Messages
import com.example.gymroutines.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.okhttp.internal.Platform
import kotlinx.coroutines.launch
import java.util.logging.Level
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository, private val profileRepository: ProfileRepository) : ViewModel() {
    private var _textMessage = MutableLiveData<String>("")
    val listMessages: LiveData<List<Messages>> =
        repository.getMessages().asLiveData()

    private val _isTextEmpty = MutableLiveData(false);
    val isTextEmpty: LiveData<Boolean>
    get() = _isTextEmpty

    private var _exception = MutableLiveData<Throwable?>(null)
    val exception get() = _exception


    fun setTextMessage(text: String) {
        _textMessage.value = text
        _isTextEmpty.value = text.isNullOrEmpty()
    }

    fun createMessage() {
        val cacahuetes = repository.createMessage(_textMessage.value!!)
        Platform.logger.log(Level.INFO, cacahuetes.toString())
    }

    fun resetError(){
        exception.value = null
    }

}

