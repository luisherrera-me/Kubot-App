package com.kuby.kubot.presentation.screen.auth.register

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuby.kubot.domain.model.MessageBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(RegisterState())

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

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

    fun validateForm() {
        viewModelScope.launch {
            when {
                state.name.length < 2 -> {
                    _messageBarState.value = MessageBarState(
                        error = Exception("Name must be at least 2 characters")
                    )
                }
                state.lastName.length < 2 -> {
                    _messageBarState.value = MessageBarState(
                        error = Exception("LastName must be at least 2 characters")
                    )
                }
                state.telephone.length < 2 -> {
                    _messageBarState.value = MessageBarState(
                        error = Exception("Telephone must be at least 2 characters")
                    )
                }
                !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> {
                    _messageBarState.value = MessageBarState(
                        error = Exception("Email is not valid")
                    )
                }
                state.password.length < 4 -> {
                    _messageBarState.value = MessageBarState(
                        error = Exception("Password must be at least 8 characters")
                    )
                }
                state.confirmPassword != state.password -> {
                    _messageBarState.value = MessageBarState(
                        error = Exception("Passwords do not match")
                    )
                }
                else -> {
                    _messageBarState.value = MessageBarState(
                        message = "Successfully Registered!",
                        //error = Exception("Successfully Registered!")
                    )
                }
            }
        }

        resetMessageBarState()
    }

    private fun resetMessageBarState() {
        viewModelScope.launch {
            delay(5000) // Espera 3 segundos antes de resetear el estado de la barra de mensajes
            _messageBarState.value = MessageBarState()
        }
    }
}
