package com.example.kalkulatrix.ui

import com.example.kalkulatrix.Operation

data class KalkulatrixUIState(
    val userInput : String = "0",
    val inputEnded : Boolean = true,
    val result : Double = 0.0,
    val notEqualClicks : Int = 0,
    val operator : Operation? = null
)
