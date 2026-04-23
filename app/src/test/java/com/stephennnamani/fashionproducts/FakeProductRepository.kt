package com.stephennnamani.fashionproducts

import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.repository.ProductRepository
import com.stephennnamani.fashionproducts.domain.result.AppResult
import java.util.Collections.emptyList


class FakeProductRepository: ProductRepository {
    var result: AppResult<List<Product>> =
        AppResult.Success(emptyList())

    override suspend fun getProducts(): AppResult<List<Product>> {
        return result
    }
}