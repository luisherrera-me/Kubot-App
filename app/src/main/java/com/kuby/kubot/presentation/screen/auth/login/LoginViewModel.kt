package com.kuby.kubot.presentation.screen.auth.login

import android.os.Message
import android.os.Messenger
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kuby.kubot.domain.model.ApiRequest
import com.kuby.kubot.domain.model.ApiResponse
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.domain.repository.Repository
import com.kuby.kubot.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.annotation.Signed
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    var isValidForm by mutableStateOf(false)
        private set

    private val _signedInState: MutableState<Boolean> = mutableStateOf(false)
    val signedInState: State<Boolean> = _signedInState

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    private val _apiResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val apiResponse: State<RequestState<ApiResponse>> = _apiResponse

    init {
        viewModelScope.launch {
            repository.readSignedInState().collect { completed ->
                _signedInState.value = completed
                Log.d("LoginViewModel", signedInState.value.toString())
            }
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }
    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
    fun onShowPasswordInput(showPassword: Boolean) {
        state = state.copy(showPassword = showPassword)
    }

    fun validateForm() {
        // Make sure email and password are not empty
        viewModelScope.launch(Dispatchers.IO) {
            if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
                _messageBarState.value = MessageBarState(
                    //message = "Email is not valid",
                    error = Exception("Email is not valid")
                )
            }else if (state.password.length < 8){
                _messageBarState.value = MessageBarState(

                    error = Exception("password must be at least 8 characters")
                )
            }
            else{
                _messageBarState.value = MessageBarState(
                    message = "Successfully Registered!"

                )
            }
        }

    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveSignedInState(signedIn = signedIn)
        }
    }

    fun updateMessageBarState() {
        _messageBarState.value = MessageBarState(
            error = GoogleAccountNotFoundException()
        )
    }

    fun verifyTokenOnBackend(request: ApiRequest) {
        _apiResponse.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.verifyTokenOnBackend(request = request)
                Log.d("LoginViewModel", response.toString())
                _apiResponse.value = RequestState.Success(response)
                _messageBarState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
            }
        } catch (e: Exception) {
            _apiResponse.value = RequestState.Error(e)
            _messageBarState.value = MessageBarState(error = e)
        }
    }

}

class GoogleAccountNotFoundException(
    override val message: String? = "Google Account Not Found."
) : Exception()