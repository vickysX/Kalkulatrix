package com.example.kalkulatrix.ui

import com.example.kalkulatrix.Operation

data class KalkulatrixUIState(
    val result : Double = 0.0,
    val notEqualClicks : Int = 0,
    val isEqualClicked : Boolean = false,
    val operator : Operation? = null
)
