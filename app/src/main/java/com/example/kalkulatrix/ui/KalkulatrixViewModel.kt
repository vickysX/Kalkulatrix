package com.example.kalkulatrix.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class KalkulatrixViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KalkulatrixUIState())
    val uiState : StateFlow<KalkulatrixUIState> = _uiState.asStateFlow()

}