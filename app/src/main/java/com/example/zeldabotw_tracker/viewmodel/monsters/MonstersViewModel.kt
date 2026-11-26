package com.example.zeldabotw_tracker.viewmodel.monsters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeldabotw_tracker.data.api.model.Monster
import com.example.zeldabotw_tracker.data.api.repository.UiState
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MonstersViewModel (
    private val repository: ZeldaRepository
): ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Monster>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Monster>>> = _state.asStateFlow()
    init {
        loadMonsters()
    }

    fun loadMonsters() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            try {
                val result = repository.getMonsters()
                _state.value = UiState.Success(result)
            }catch (exception: Exception){
                _state.value = UiState.Error(
                    exception.message ?: "No fue posible cargar los enemigos"
                )
            }
        }
    }
}