package com.stephennnamani.fashionproducts.domain.repository

import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.result.AppResult

interface ProductRepository {
    suspend fun getProducts(): AppResult<List<Product>>
}