package com.example.kalkulatrix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kalkulatrix.ui.KalkulatrixViewModel
import com.example.kalkulatrix.ui.ScientificCalculatorScreen
import com.example.kalkulatrix.ui.StandardCalculatorScreen
import com.example.kalkulatrix.ui.components.KalkDisplay
import com.example.kalkulatrix.ui.theme.KalkulatrixTheme

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
    val currentScreen by navController.currentBackStackEntryAsState()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column {
            KalkDisplay(
                userInput = kalkUIState.userInput
            )
            IconButton(
                onClick = {
                    switchKalk(navController, currentScreen)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = null
                )
            }
            Divider()
        }
        NavHost(
            navController = navController,
            startDestination = CurrentKalk.Standard.name
        ){
            composable(route = CurrentKalk.Standard.name) {
                StandardCalculatorScreen(
                    onOperationButtonClicked = {
                        viewModel.applyOperator(it)
                    },
                    onNumberClicked = {viewModel.updateUserInput(it)}
                )
            }
            composable(route = CurrentKalk.Scientific.name) {
                ScientificCalculatorScreen()
            }
        }
    }
}

private fun switchKalk(
    navController: NavController,
    currentScreen : NavBackStackEntry?
) {
    when (currentScreen?.destination?.route) {
        CurrentKalk.Standard.name -> navController
            .navigate(CurrentKalk.Scientific.name)
        else -> navController.navigate(CurrentKalk.Standard.name)
    }
}

@Composable
@Preview(showSystemUi = true)
fun KalkAppPreview() {
    KalkulatrixTheme {
        KalkulatrixApp()
    }
}

