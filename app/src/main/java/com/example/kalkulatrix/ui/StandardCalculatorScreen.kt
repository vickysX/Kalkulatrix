package com.example.kalkulatrix.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalkulatrix.Operation
import com.example.kalkulatrix.R
import com.example.kalkulatrix.ui.components.NumericKeyboard
import com.example.kalkulatrix.ui.components.OperationButton
import com.example.kalkulatrix.ui.theme.KalkulatrixTheme

private val horizontalRow = listOf(Operation.Reset, Operation.Percentage, Operation.Division)
private val verticalColumn = listOf(
    Operation.Multiplication,
    Operation.Subtraction,
    Operation.Addition,
    Operation.Equals
)

@Composable
fun StandardCalculatorScreen(
    modifier: Modifier = Modifier,
    onOperationButtonClicked : (Operation) -> Unit,
    onNumberClicked : (String) -> Unit
) {
    val space = dimensionResource(id = R.dimen.btn_space)
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = space)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = space),
        ) {
            horizontalRow.map {
                OperationButton(
                    onButtonClicked = onOperationButtonClicked,
                    operation = it,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(space)
        ) {
            NumericKeyboard(
                onNumberClicked = onNumberClicked
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(space)
            ) {
               verticalColumn.map {
                   OperationButton(
                       onButtonClicked = onOperationButtonClicked,
                       operation = it
                   )
               }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LightStandardScreen() {
    KalkulatrixTheme(darkTheme = false) {
        StandardCalculatorScreen(
            onOperationButtonClicked = {},
            onNumberClicked = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DarkStandardScreen() {
    KalkulatrixTheme(darkTheme = true) {
        StandardCalculatorScreen(
            onOperationButtonClicked = {},
            onNumberClicked = {}
        )
    }
}