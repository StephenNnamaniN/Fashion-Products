package com.stephennnamani.fashionproducts.domain.usescase

import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.repository.ProductRepository
import com.stephennnamani.fashionproducts.domain.result.AppResult

class GetProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): AppResult<List<Product>> {
        return repository.getProducts()
    }
}