package com.example.walmartapp.utils

/**
 * Sealed class representing the result state of an operation.
 *
 * This class is used to encapsulate the different possible states of a result
 * from an operation, such as a network request or a database query.
 *
 * It can represent loading, success, or error states.
 *
 * @param T The type of the data returned in the success state.
 */
sealed class ResultState<out T> {
    data object Loading : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val error: Exception) : ResultState<Nothing>()
}
