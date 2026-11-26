package com.example.zeldabotw_tracker.viewmodel.monsters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository

class MonstersViewModelFactory (
    private val repository: ZeldaRepository

): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(
        modelClass: Class<T>
    ): T{
        if (modelClass.isAssignableFrom(MonstersViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MonstersViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Desconocido")
    }
}