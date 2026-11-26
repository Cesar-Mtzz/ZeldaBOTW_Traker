package com.example.zeldabotw_tracker.viewmodel.equipments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeldabotw_tracker.data.api.model.Equipment
import com.example.zeldabotw_tracker.data.api.repository.UiState
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquipmentsViewModel (
    private val repository: ZeldaRepository
): ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Equipment>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Equipment>>> = _state.asStateFlow()
    init {
        loadEquipments()
    }

    fun loadEquipments() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            try {
                val result = repository.getEquipments()
                _state.value = UiState.Success(result)
            }catch (exception: Exception){
                _state.value = UiState.Error(
                    exception.message ?: "No fue posible cargar el equipamiento"
                )
            }
        }
    }
}