package com.stephennnamani.fashionproducts

import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.result.AppError
import com.stephennnamani.fashionproducts.domain.result.AppResult
import com.stephennnamani.fashionproducts.domain.usescase.GetProductUseCase
import com.stephennnamani.fashionproducts.presentation.state.ProductUiState
import com.stephennnamani.fashionproducts.presentation.viewmodel.ProductViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {
    private lateinit var fakeProductRepository: FakeProductRepository
    private lateinit var useCase: GetProductUseCase
    private lateinit var viewModel: ProductViewModel

    @Before
    fun setUp(){
        fakeProductRepository = FakeProductRepository()
        useCase = GetProductUseCase(fakeProductRepository)
        viewModel = ProductViewModel(useCase)
    }


    @Test
    fun `emits Loading then Success when use case succeeds`() = runTest {
        val products = listOf(Product("1", "Shoes", "£50", true))
        fakeProductRepository.result = AppResult.Success(products)

        viewModel.fetchProducts()
        advanceUntilIdle()

        assertEquals(ProductUiState.Success(products), viewModel.uiState.value)
    }

    @Test
    fun `emits Loading then Error when useCase fails`() = runTest {
        fakeProductRepository.result = AppResult.Failure(AppError.NetworkError)

        viewModel.fetchProducts()
        advanceUntilIdle()
        assertEquals(ProductUiState.Error("Something went wrong."), viewModel.uiState.value)
    }
}