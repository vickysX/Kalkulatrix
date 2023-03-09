package com.example.kalkulatrix.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kalkulatrix.Operation

@Composable
fun StandardCalculatorScreen(
    modifier: Modifier = Modifier,
    onOperationButtonClicked : (Pair<Operation, String>) -> Unit,
    onNumberClicked : (String) -> Unit
) {

}