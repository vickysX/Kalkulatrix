package com.example.kalkulatrix.ui

import com.example.kalkulatrix.Operation

data class KalkulatrixUIState(
    val userInput : String = "",
    val result : Double = 0.0,
    val notEqualClicks : Int = 0,
    val operator : Operation? = null
)
