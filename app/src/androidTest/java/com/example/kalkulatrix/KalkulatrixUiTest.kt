package com.example.kalkulatrix

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kalkulatrix.ui.theme.KalkulatrixTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class KalkulatrixUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testNumericInput() {
        composeRule.setContent {
            KalkulatrixTheme {
                KalkulatrixApp()
            }
        }
        composeRule.onNodeWithText("1").performClick()
        composeRule.onNodeWithText(".").performClick()
        composeRule.onNodeWithText("5").performClick()
        composeRule.onNodeWithText("1.5").assertExists()
    }

    @Test
    fun testOperation() {
        composeRule.setContent {
            KalkulatrixTheme {
                KalkulatrixApp()
            }
        }
        composeRule.onNodeWithText("3").performClick()
        composeRule.onNodeWithText("\u00d7").performClick()
        composeRule.onNodeWithText("5").performClick()
        composeRule.onNodeWithText("=").performClick()
        composeRule.onNodeWithText("15.0").assertExists()
    }
}