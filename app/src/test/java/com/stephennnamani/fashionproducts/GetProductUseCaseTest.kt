package com.stephennnamani.fashionproducts

import com.stephennnamani.fashionproducts.domain.model.Product
import com.stephennnamani.fashionproducts.domain.result.AppResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetProductUseCaseTest {
    private lateinit var useCase: GetProductUseCase
    private lateinit var fakeRepository: FakeProductRepository

    @Before
    fun setUp() {
        fakeRepository = FakeProductRepository()
        useCase = GetProductUseCase(fakeRepository)
    }

    @Test
    fun `returns success when repository returns success`() = runTest {
        val products = listOf(
            Product("1", "Shoes", "£50", true)
        )

        fakeRepository.result = AppResult.Success(products)

        val result = useCase()

        assertEquals(AppResult.Success(products), result)
    }
}