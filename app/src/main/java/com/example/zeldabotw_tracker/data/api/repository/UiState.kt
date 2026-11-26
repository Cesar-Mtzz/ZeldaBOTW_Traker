package com.example.zeldabotw_tracker.data.api.repository

sealed interface UiState<out T> {
    data object Loading: UiState<Nothing>
    data class Success<T>(val data: T): UiState<T>
    data class Error(val message: String): UiState<Nothing>
}