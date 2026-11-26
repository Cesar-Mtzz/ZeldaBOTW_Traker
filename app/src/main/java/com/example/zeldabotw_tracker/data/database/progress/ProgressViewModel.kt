package com.example.zeldabotw_tracker.data.database.progress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeldabotw_tracker.data.database.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProgressViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val progressDao = AppDatabase.getInstance(application).progressDAO()
    private val repository = ProgressRepository(progressDao)

    private val progressStateMap = mutableMapOf<Int, StateFlow<Progress?>>()

    fun getProgress(userId: Int): StateFlow<Progress?> {
        return progressStateMap.getOrPut(userId) {
            repository.getProgress(userId)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Eagerly,
                    initialValue = null
                )
        }
    }

    fun updateProgress(progress: Progress) {
        viewModelScope.launch {
            repository.saveProgress(progress)
        }
    }

    fun toggleEnemy(userId: Int, enemyId: Int, isAdd: Boolean) {
        viewModelScope.launch {
            repository.toggleEnemy(userId, enemyId, isAdd)
        }
    }

    fun toggleFood(userId: Int, foodId: Int, isAdd: Boolean) {
        viewModelScope.launch {
            repository.toggleFood(userId, foodId, isAdd)
        }
    }

    fun toggleEquipment(userId: Int, equipmentId: Int, isAdd: Boolean) {
        viewModelScope.launch {
            repository.toggleEquipment(userId, equipmentId, isAdd)
        }
    }
}
