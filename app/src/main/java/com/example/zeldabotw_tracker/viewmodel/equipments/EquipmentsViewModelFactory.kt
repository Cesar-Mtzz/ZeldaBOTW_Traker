package com.example.zeldabotw_tracker.viewmodel.equipments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository

class EquipmentsViewModelFactory (
    private val repository: ZeldaRepository

): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(
        modelClass: Class<T>
    ): T{
        if (modelClass.isAssignableFrom(EquipmentsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return EquipmentsViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Desconocido")
    }
}