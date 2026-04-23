package com.stephennnamani.fashionproducts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.presentation.state.ProductUiState
import com.stephennnamani.fashionproducts.presentation.ui.component.ProductScreenContent
import org.junit.Rule
import org.junit.Test

class ProductScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `show loading content when ui state is Loading`() {
        composeTestRule.setContent {
            ProductScreenContent(
                uiState = ProductUiState.Loading,
                onRetry = {}
            )
        }
    }

    @Test
    fun `show products when ui state is Success`() {
        val products = listOf(
            Product("1", "Shoes", "£50", true),
            Product("2", "Hat", "£20", true),
            Product("3", "Glasses", "£10", false)
        )

        composeTestRule.setContent {
            ProductScreenContent(
                uiState = ProductUiState.Success(products),
                onRetry = {}
            )
        }

        composeTestRule.onNodeWithText("Shoes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hat").assertIsDisplayed()
        composeTestRule.onNodeWithText("Glasses").assertIsDisplayed()
    }

    @Test
    fun `shows retry button when ui is Error`() {
        composeTestRule.setContent {
            ProductScreenContent(
                uiState = ProductUiState.Error("Please check your connection and try again."),
                onRetry = {}
            )
        }
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }

    @Test
    fun `calls onRetry when retry button is clicked`(){
        var retryClicked = false

        composeTestRule.setContent {
            ProductScreenContent(
                uiState = ProductUiState.Error("Please check your connection and try again."),
                onRetry = { retryClicked = true }
            )
        }
        composeTestRule.onNodeWithText("Retry").performClick()
        assert(retryClicked)
    }

}