package com.example.kalkulatrix.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalkulatrix.Operation
import com.example.kalkulatrix.R
import com.example.kalkulatrix.ui.theme.KalkulatrixTheme

@Composable
fun OperationButton(
    modifier: Modifier = Modifier,
    onButtonClicked : (Operation) -> Unit,
    operation : Operation,
    isReset : Boolean = operation == Operation.Reset
) {
    val padding = dimensionResource(id = R.dimen.btn_padding)
    val size = dimensionResource(id = R.dimen.btn_size)
    val width = dimensionResource(id = R.dimen.big_btn_width)
    val op = stringResource(id = operation.operator)
    val buttonColor = if (isReset) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.primary
    }
    val cColor = if (isReset) {
        MaterialTheme.colorScheme.onError
    } else {
        MaterialTheme.colorScheme.onPrimary
    }
    Button(
        onClick = { onButtonClicked(operation) },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        shape = CircleShape,
        modifier = when {
            isReset -> modifier
                .height(size)
                .width(width)
                .padding(padding)
            else -> modifier
                .size(size)
                .padding(padding)
        }

    ) {
        Text(
            text = op,
            color = cColor,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

val numbers = listOf(
    listOf(R.string.seven, R.string.eight, R.string.nine),
    listOf(R.string.four, R.string.five, R.string.six),
    listOf(R.string.one, R.string.two, R.string.three),
    listOf(R.string.zero, R.string.floating_point)
)

@Composable
fun NumericKeyboard(
    modifier: Modifier = Modifier,
    nums : List<List<Int>> = numbers,
    onNumberClicked: (String) -> Unit
) {
    val space = dimensionResource(id = R.dimen.btn_space)
    Column(
        verticalArrangement = Arrangement.spacedBy(space)
    ) {
        nums.map {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space)
            ) {
                it.map {num ->
                   NumericButton(
                       number = num,
                       onNumberClicked = onNumberClicked
                   )
                }
            }
        }
    }
}

@Composable
fun NumericButton(
    modifier: Modifier = Modifier,
    @StringRes number : Int,
    onNumberClicked : (String) -> Unit,
    isZero : Boolean = number == R.string.zero
) {
    val size = dimensionResource(id = R.dimen.btn_size)
    val width = dimensionResource(id = R.dimen.big_btn_width)
    val padding = dimensionResource(id = R.dimen.btn_padding)
    val num = stringResource(id = number)
    Button(
        onClick = { onNumberClicked(num) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        shape = CircleShape,
        modifier = when {
            isZero -> modifier
                .height(height = size)
                .width(width = width)
                .padding(all = padding)
            else -> modifier
                .size(size = size)
                .padding(all = padding)
        }
                ) {
                Text(
                    text = num,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .width(16.dp)
                    /*.padding(4.dp)*/,
                    textAlign = TextAlign.Center
                )
    }
}


@Composable
fun KalkDisplay(
    modifier: Modifier = Modifier,
    userInput : String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(start = 8.dp, end = 8.dp, top = 80.dp, bottom = 80.dp)
    ) {
        Text(
            text = userInput,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.End
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OperationButtonPreview() {
    KalkulatrixTheme(darkTheme = true) {
        OperationButton(
            onButtonClicked = {},
            operation = Operation.Equals
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ResetButtonPreview() {
    KalkulatrixTheme(darkTheme = true) {
        OperationButton(
            onButtonClicked = {},
            operation = Operation.Reset
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NumericButtonPreview() {
    KalkulatrixTheme() {
        NumericButton(
            number = R.string.one,
            onNumberClicked = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ZeroButtonPreview() {
    KalkulatrixTheme() {
        NumericButton(
            number = R.string.zero,
            onNumberClicked = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NumericKeyboardPreview() {
    KalkulatrixTheme() {
        NumericKeyboard(onNumberClicked = {})
    }
}