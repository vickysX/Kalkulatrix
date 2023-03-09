package com.example.kalkulatrix.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalkulatrix.Operation
import com.example.kalkulatrix.R
import com.example.kalkulatrix.ui.theme.KalkulatrixTheme

@Composable
fun OperationButton(
    modifier: Modifier = Modifier,
    onButtonClicked : (Pair<Operation, String>) -> Unit,
    operation : Operation
) {
    val op = stringResource(id = operation.operator)
    Button(onClick = { onButtonClicked(Pair(operation, op)) }) {
        Text(
            text = op
        )
    }
}

@Composable
fun NumericButton(
    modifier: Modifier = Modifier,
    @StringRes number : Int,
    onNumberClicked : (String) -> Unit,
    isZero : Boolean = number == R.string.zero
) {
    val num = stringResource(id = number)
    Button(onClick = { onNumberClicked(num) }) {
        Text(
            text = num
        )
    }
}

@Composable
fun KalkDisplay(
    modifier: Modifier = Modifier,
    userInput : String
) {
    Row() {
        Text(
            text = userInput,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonPreview() {
    KalkulatrixTheme() {
        NumericButton(
            number = R.string.one,
            onNumberClicked = {

            }
        )
    }
}