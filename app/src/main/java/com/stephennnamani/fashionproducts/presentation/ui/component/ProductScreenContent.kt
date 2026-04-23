package com.stephennnamani.fashionproducts.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.presentation.state.ProductUiState


@Composable
fun ProductScreenContent(
    uiState: ProductUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
){
    when(uiState) {
        ProductUiState.Idle -> Unit

        ProductUiState.Loading -> {
            LoadingContent(modifier = modifier)
        }

        is ProductUiState.Success -> {
            ProductListContent(
                products = uiState.products,
                modifier = modifier
            )
        }

        is ProductUiState.Error -> {
            ErrorContent(
                message = uiState.message,
                onRetry = onRetry,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ProductListContent(
    products: List<Product>,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = products,
            key = {it.id }
        ){ product ->
            ProductRow(product = product)
        }

    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .semantics { contentDescription = "Sorry, an error occured"}
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Retry")
        }
    }
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .semantics { contentDescription = "Loading products"},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(
            text = "Loading products...",
            modifier = Modifier.padding(top = 16.dp)
        )

    }
}