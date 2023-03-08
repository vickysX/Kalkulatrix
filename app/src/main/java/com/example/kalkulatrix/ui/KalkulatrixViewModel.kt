package com.example.kalkulatrix.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kalkulatrix.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KalkulatrixViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KalkulatrixUIState())
    val uiState : StateFlow<KalkulatrixUIState> = _uiState.asStateFlow()

    var userInput by mutableStateOf("")
        private set

    init {
        resetKalk()
    }

    private fun resetKalk() {
        _uiState.value = KalkulatrixUIState()
    }

    fun updateUserInput(input : String) {
        val sb = StringBuilder(userInput)
        sb.append(input)
        userInput = sb.toString()
    }

    fun applyOperator(operation : Operation) {
        if (operation == Operation.Equals) {
            onEqualPressed()
        } else if (operation == Operation.Reset) {
            resetKalk()
        } else {
            var notEqualClicks = _uiState.value.notEqualClicks
            when (notEqualClicks) {
                0 -> _uiState.update{ currentState ->
                    currentState.copy(
                        result = userInput.toDouble(),
                        operator = operation
                    )
                }
                else -> calculateResult(
                    _uiState.value.result,
                    userInput.toDouble(),
                    _uiState.value.operator
                )
            }
            notEqualClicks++
            _uiState.update { currentState ->
                currentState.copy(
                    notEqualClicks = notEqualClicks
                )
            }
        }
    }

    private fun onEqualPressed() {
        calculateResult(
            _uiState.value.result,
            userInput.toDouble(),
            _uiState.value.operator
        )
        _uiState.update { currentState ->
            currentState.copy(
                notEqualClicks = 0
            )
        }
    }

    private fun calculateResult(
        num1 : Double,
        num2 : Double,
        operation: Operation?
    ) {
        val result = when (operation) {
            Operation.Addition -> add(num1, num2)
            Operation.Subtraction -> subtract(num1, num2)
            Operation.Multiplication -> mult(num1, num2)
            Operation.Percentage -> calcPerc(num1, num2)
            else -> divide(num1, num2)
        }
        _uiState.update {currentState ->
            currentState.copy(
                result = result,
                operator = null
            )
        }
    }

    private val add : (Double, Double) -> Double = {num1, num2 ->
        num1 + num2
    }

    private val subtract : (Double, Double) -> Double = {num1, num2 ->
        num1 - num2
    }

    private val mult : (Double, Double) -> Double = {num1, num2 ->
        num1 * num2
    }

    private val divide : (Double, Double) -> Double = {num1, num2 ->
        num1 / num2
    }

    private val calcPerc : (Double, Double) -> Double = {num1, num2 ->
        mult(divide(num1, 100.0), num2)
    }
}