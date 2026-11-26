package com.example.zeldabotw_tracker.viewmodel.foods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeldabotw_tracker.data.api.model.Food
import com.example.zeldabotw_tracker.data.api.repository.UiState
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodsViewModel (
    private val repository: ZeldaRepository
): ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Food>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Food>>> = _state.asStateFlow()
    init {
        loadFoods()
    }

    fun loadFoods() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            try {
                val result = repository.getFoods()
                _state.value = UiState.Success(result)
            }catch (exception: Exception){
                _state.value = UiState.Error(
                    exception.message ?: "No fue posible cargar las comidas"
                )
            }
        }
    }
}