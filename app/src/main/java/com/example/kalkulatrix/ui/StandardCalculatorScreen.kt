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
            /*modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()*/
                //.weight(1f)
        ) {
            horizontalRow.map {
                OperationButton(
                    onButtonClicked = onOperationButtonClicked,
                    operation = it,
                    /*modifier = when (it) {
                        Operation.Reset -> Modifier.weight(2f)
                        else -> Modifier.weight(1f)
                    }*/

                )
            }
        }
        Row {
            NumericKeyboard(
                onNumberClicked = onNumberClicked
            )
            Column() {
               verticalColumn.map {
                   OperationButton(
                       onButtonClicked = onOperationButtonClicked,
                       operation = it
                   )
               }
            }
        }
        /*LazyRow(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            items(horizontalRow) {
                OperationButton(
                    onButtonClicked = onOperationButtonClicked,
                    operation = it
                )
            }
        }*/
        /*Row(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
        ) {
            NumericKeyboard(
                onNumberClicked = onNumberClicked,
                modifier = Modifier
                    .weight(3f)
                    .wrapContentWidth(Alignment.Start)
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            ) {
                items(verticalColumn) {
                    OperationButton(
                        onButtonClicked = onOperationButtonClicked,
                        operation = it
                    )
                }
            }
        }*/
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