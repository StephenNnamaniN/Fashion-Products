package com.stephennnamani.fashionproducts.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.stephennnamani.fashionproducts.domain.model.Product


@Composable
fun ProductRow(
    product: Product,
    modifier: Modifier = Modifier
) {
    val stockText = if (product.inStock) "In stock" else "Out of Stock"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription =
                    "${product.name}, price ${product.price}, $stockText"
            }
    ) {
        Column(
            modifier.padding(16.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stockText,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}