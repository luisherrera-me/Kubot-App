package com.kuby.kubot.presentation.screen.auth.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.domain.model.User
import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.domain.useCase.auth.AuthUseCase
import com.kuby.kubot.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var state by mutableStateOf(RegisterState())

    val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    private val _logInState: MutableState<Boolean> = mutableStateOf(false)
    val LogInState: State<Boolean> = _logInState

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onLastNameInput(lastName: String) {
        state = state.copy(lastName = lastName)
    }

    fun onPhoneInput(telephone: String) {
        state = state.copy(telephone = telephone)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onShowPassword(showPassword: Boolean) {
        state = state.copy(showPassword = showPassword)
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onShowConfirmPassword(showConfirmPassword: Boolean) {
        state = state.copy(showConfirmPassword = showConfirmPassword)
    }

    fun validateForm(): Boolean {
        when {
            state.name.length < 2 -> {
                _messageBarState.value = MessageBarState(
                    error = Exception("Name must be at least 2 characters")
                )
                return false
            }
            state.lastName.length < 2 -> {
                _messageBarState.value = MessageBarState(
                    error = Exception("LastName must be at least 2 characters")
                )
                return false
            }
            state.telephone.length < 2 -> {
                _messageBarState.value = MessageBarState(
                    error = Exception("Telephone must be at least 2 characters")
                )
                return false
            }
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> {
                _messageBarState.value = MessageBarState(
                    error = Exception("Email is not valid")
                )
                return false
            }
            state.password.length < 4 -> {
                _messageBarState.value = MessageBarState(
                    error = Exception("Password must be at least 8 characters")
                )
                return false
            }
            state.confirmPassword != state.password -> {
                _messageBarState.value = MessageBarState(
                    error = Exception("Passwords do not match")
                )
                return false

            }

            else -> {
                return true
            }
        }
    }

    var registerResponse by mutableStateOf<Resource<AuthResponse>?>(null)
        private set


    fun saveSession(authResponse: AuthResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.saveSession(authResponse)
        }
    }

    fun login() = viewModelScope.launch(Dispatchers.IO) {
        if (validateForm()){
            val data = User(
            name = state.name,
            lastName = state.lastName,
            emailAddress = state.email,
            password = state.password,
            phone = state.telephone
            )
            _logInState.value = validateForm()
            registerResponse = Resource.Loading
            val result = authUseCase.register(data)
            registerResponse = result
            Log.d("LoginViewModel", registerResponse.toString())
            _logInState.value = false
        }

    }



}
