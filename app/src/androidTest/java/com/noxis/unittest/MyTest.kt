package com.noxis.unittest

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class MyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMyButton() {
        // Create the Jetpack Compose view
        val myButton = @Composable {

            var state by remember { mutableStateOf(false) }
            Button(onClick = { state = !state }) {
                Text("Click me")
            }

            if (state) {
                Text("Welcome!")
            }
        }
        // Set the view as the content of the Activity
        composeTestRule.setContent {
            myButton()
        }
        // Find the Button view and perform a click action
        composeTestRule.onNodeWithText("Click me").performClick()
        composeTestRule.onNodeWithText("Welcome!").assertIsDisplayed()

    }


}