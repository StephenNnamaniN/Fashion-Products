package com.stephennnamani.fashionproducts.domain.result

sealed interface AppError {
    data object NetworkError : AppError
    data object ServerError : AppError
    data object UnknowError : AppError
}