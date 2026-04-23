package com.stephennnamani.fashionproducts

import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.result.AppResult
import com.stephennnamani.fashionproducts.domain.usescase.GetProductUseCase
import com.stephennnamani.fashionproducts.presentation.state.ProductUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProductViewModelTest {
    private lateinit var fakeProductRepository: FakeProductRepository
    private lateinit var useCase: GetProductUseCase
    private lateinit var viewModel: ProductViewModel

    @Before
    fun SetUp(){
        fakeProductRepository = FakeProductRepository()
        useCase = GetProductUseCase(fakeProductRepository)
        viewModel = ProductViewModel(useCase)
    }

    @Test
    fun `emits Loading then Success when usecase succeeds`() = runTest {
        val products = listOf(Product("1", "Shoes", "£50", true))
        fakeProductRepository.result = AppResult.Success(products)

        viewModel.fetchProducts()

        assertEquals(ProductUiState.Success(products), viewModel.uiState.value)
    }
}