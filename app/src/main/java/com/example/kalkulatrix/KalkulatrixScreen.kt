package com.example.kalkulatrix

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kalkulatrix.ui.KalkulatrixViewModel
import com.example.kalkulatrix.ui.ScientificCalculatorScreen
import com.example.kalkulatrix.ui.StandardCalculatorScreen

enum class CurrentKalk {
    Standard, Scientific
}

@Composable
fun KalkulatrixApp(
    modifier: Modifier = Modifier,
    viewModel: KalkulatrixViewModel = viewModel()
) {
    val navController = rememberNavController()
    val kalkUIState by viewModel.uiState.collectAsState()
    Column() {
        NavHost(
            navController = navController,
            startDestination = CurrentKalk.Standard.name
        ){
            composable(route = CurrentKalk.Standard.name) {
                StandardCalculatorScreen()
            }
            composable(route = CurrentKalk.Scientific.name) {
                ScientificCalculatorScreen()
            }
        }
    }
}

