package com.example.kalkulatrix.ui

import androidx.lifecycle.ViewModel
import com.example.kalkulatrix.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KalkulatrixViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KalkulatrixUIState())
    val uiState : StateFlow<KalkulatrixUIState> = _uiState.asStateFlow()


    init {
        resetKalk()
    }

    private fun resetKalk() {
        _uiState.value = KalkulatrixUIState()
    }

    fun updateUserInput(input : String) {
        val sb = when  {
            (input != "." || !_uiState.value.userInput.contains("."))
                    && _uiState.value.inputEnded  -> StringBuilder()

            else -> StringBuilder(_uiState.value.userInput)
        }
        sb.append(input)
         _uiState.update { currentState ->
             currentState.copy(
                 userInput = sb.toString(),
                 inputEnded = false
             )
         }
    }

    fun applyOperator(operator : Operation) {
        _uiState.update {
            it.copy(
                inputEnded = true
            )
        }
        if (operator == Operation.Equals) {
            onEqualPressed()
        } else if (operator == Operation.Reset) {
            resetKalk()
        } else {
            var notEqualClicks = _uiState.value.notEqualClicks
            when (notEqualClicks) {
                0 -> _uiState.update{ currentState ->
                    currentState.copy(
                        result = _uiState.value.userInput.toDouble(),
                        //operator = operator
                    )
                }
                else -> calculateResult(
                    _uiState.value.result,
                    _uiState.value.userInput.toDouble(),
                    _uiState.value.operator
                )
            }
            notEqualClicks++
            _uiState.update { currentState ->
                currentState.copy(
                    notEqualClicks = notEqualClicks,
                    operator = operator
                )
            }
        }
    }

    private fun onEqualPressed() {
        calculateResult(
            _uiState.value.result,
            _uiState.value.userInput.toDouble(),
            _uiState.value.operator
        )
        _uiState.update { currentState ->
            currentState.copy(
                notEqualClicks = 0,
                userInput = _uiState.value.result.toString(),
            )
        }
    }

    private fun calculateResult(
        num1 : Double,
        num2 : Double,
        operation: Operation?
    ) {
        val result = when (operation) {
            Operation.Addition -> num1 + num2
            Operation.Subtraction -> num1 - num2
            Operation.Multiplication -> num1 * num2
            Operation.Percentage -> (num1 / 100.0) * num2
            else -> num1 / num2
        }
        _uiState.update {currentState ->
            currentState.copy(
                result = result
            )
        }
    }

}