package com.example.kalkulatrix

import com.example.kalkulatrix.ui.KalkulatrixViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KalkulatrixUnitTest {

    private val viewModel = KalkulatrixViewModel()

    @Test
    fun additionWithTwoTerms_isCorrect() {
        viewModel.updateUserInput("10")
        viewModel.applyOperator(Operation.Addition)
        viewModel.updateUserInput("5")
        viewModel.applyOperator(Operation.Equals)
        val currentUiState = viewModel.uiState.value
        assertEquals(15.0, currentUiState.result, 0.001)
    }

    @Test
    fun additionWithMoreTerms_isCorrect() {
        viewModel.updateUserInput("0.5")
        viewModel.applyOperator(Operation.Addition)
        viewModel.updateUserInput("0.5")
        viewModel.applyOperator(Operation.Addition)
        viewModel.updateUserInput("2")
        viewModel.applyOperator(Operation.Equals)
        val currentUiState = viewModel.uiState.value
        assertEquals(3.0, currentUiState.result, 0.001)
    }

    @Test
    fun mixedExpressionWithManyTerms_isCorrect() {
        viewModel.updateUserInput("2")
        viewModel.applyOperator(Operation.Percentage)
        viewModel.updateUserInput("200")
        viewModel.applyOperator(Operation.Multiplication)
        viewModel.updateUserInput("25")
        viewModel.applyOperator(Operation.Division)
        viewModel.updateUserInput("10")
        viewModel.applyOperator(Operation.Equals)
        val currentUiState = viewModel.uiState.value
        assertEquals(10.0, currentUiState.result, 0.001)
    }
}