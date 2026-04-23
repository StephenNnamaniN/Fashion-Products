package com.stephennnamani.fashionproducts.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.stephennnamani.fashionproducts.presentation.ui.component.ProductScreenContent
import com.stephennnamani.fashionproducts.presentation.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    viewModel: ProductViewModel
){
    val uiState by viewModel.uiState.collectAsState()

    ProductScreenContent(
        uiState = uiState,
        onRetry = viewModel::fetchProducts
    )
}