package com.example.zeldabotw_tracker.viewmodel.foods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository

class FoodsViewModelFactory (
    private val repository: ZeldaRepository

): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(
        modelClass: Class<T>
    ): T{
        if (modelClass.isAssignableFrom(FoodsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FoodsViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Desconocido")
    }
}