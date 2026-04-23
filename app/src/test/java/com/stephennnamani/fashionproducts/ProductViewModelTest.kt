package com.stephennnamani.fashionproducts

import app.cash.turbine.test
import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.result.AppError
import com.stephennnamani.fashionproducts.domain.result.AppResult
import com.stephennnamani.fashionproducts.domain.usescase.GetProductUseCase
import com.stephennnamani.fashionproducts.presentation.state.ProductUiState
import com.stephennnamani.fashionproducts.presentation.viewmodel.ProductViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

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

        viewModel.uiState.test {
            assertEquals(ProductUiState.Idle, awaitItem())

            viewModel.fetchProducts()

            assertEquals(ProductUiState.Loading, awaitItem())
            assertEquals(ProductUiState.Success(products), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `emits Loading then Error when useCase fails`() = runTest {
        fakeProductRepository.result = AppResult.Failure(AppError.NetworkError)

        viewModel.uiState.test {
            assertEquals(ProductUiState.Idle, awaitItem())
            viewModel.fetchProducts()

            assertEquals(ProductUiState.Loading, awaitItem())
            assertEquals(ProductUiState.Error("Please check your connection and try again."),
                awaitItem()
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
}