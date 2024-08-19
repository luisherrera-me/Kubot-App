package com.kuby.kubot.presentation.screen.client.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuby.kubot.domain.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {
    fun logout() = viewModelScope.launch {
        authUseCase.deleteSession()
    }

}