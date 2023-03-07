package com.example.kalkulatrix.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.*
import com.example.kalkulatrix.ui.theme.KalkulatrixTheme

@Composable
fun KalkulatrixScreen(
    modifier: Modifier = Modifier,
    viewModel: KalkulatrixViewModel = viewModel()
) {
    val kalkUIState by viewModel.uiState.collectAsState()
}

@Composable
@Preview(showSystemUi = true)
fun KalkLightThemePreview() {
    KalkulatrixTheme(darkTheme = false) {
        KalkulatrixScreen()
    }
}