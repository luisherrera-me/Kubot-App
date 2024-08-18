package com.kuby.kubot.presentation.screen.auth.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuby.kubot.domain.model.ApiRequest
import com.kuby.kubot.domain.model.ApiResponse
import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.domain.repository.Repository
import com.kuby.kubot.domain.useCase.auth.AuthUseCase
import com.kuby.kubot.util.RequestState
import com.kuby.kubot.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    var isValidForm by mutableStateOf(false)
        private set

    private val _signedInState: MutableState<Boolean> = mutableStateOf(false)
    val signedInState: State<Boolean> = _signedInState

    private val _logInState: MutableState<Boolean> = mutableStateOf(false)
    val LogInState: State<Boolean> = _logInState

    val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    private val _apiResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val apiResponse: State<RequestState<ApiResponse>> = _apiResponse

    init {
        getSession()
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

    var loginResponse by mutableStateOf<Resource<AuthResponse>?>(null)

    fun saveSession(authResponse: AuthResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.saveSession(authResponse)
        }
    }

    fun getSession() {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.getSessionData().collect(){ data ->
                if (!data.token.isNullOrBlank()){
                    loginResponse = Resource.Success(data)
                }
            }
        }
    }


    fun login() = viewModelScope.launch(Dispatchers.IO) {
        if (validateForm()){
            val data = LoginResponse(
                emailAddress = state.email,
                password = state.password
            )
            _logInState.value = validateForm()
            loginResponse = Resource.Loading
            val result = authUseCase.login(data)
            loginResponse = result
            Log.d("LoginViewModel", loginResponse.toString())
            _logInState.value = false
        }

    }

    fun validateForm(): Boolean {
        // Make sure email and password are not empty
        if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            _messageBarState.value = MessageBarState(
                error = Exception("Email is not valid")
            )
            return false
        }else if (state.password.length < 3){
            _messageBarState.value = MessageBarState(
                error = Exception("password must be at least 8 characters")
            )
            return false
        }
        else{
            return true
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