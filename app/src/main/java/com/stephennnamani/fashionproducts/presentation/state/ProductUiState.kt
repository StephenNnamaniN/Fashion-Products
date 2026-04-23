package com.stephennnamani.fashionproducts.presentation.state

import com.stephennnamani.fashionproducts.domain.model.Product

sealed interface ProductUiState {
    data object Idle: ProductUiState
    data object Loading : ProductUiState
    data class Success(val products: List<Product>): ProductUiState
    data class Error(val message: String): ProductUiState
}