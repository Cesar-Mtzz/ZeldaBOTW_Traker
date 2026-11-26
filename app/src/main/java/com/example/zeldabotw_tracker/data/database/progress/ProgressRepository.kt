package com.example.zeldabotw_tracker.data.database.progress

import kotlinx.coroutines.flow.Flow

class ProgressRepository (
    private val progressDao: ProgressDao
) {
    suspend fun newProgress(userId: Int){
        val new = Progress(
            userId = userId,
            quantity_enemies = 0,
            quantity_foods = 0,
            quantity_equipment = 0,
            found_enemies = "",
            found_foods = "",
            found_equipment = ""
        )
        progressDao.saveProgress(new)
    }

    fun getProgress(userId: Int): Flow<Progress?> {
        return progressDao.getProgressByUser(userId)
    }

    suspend fun saveProgress(progress: Progress) {
        progressDao.saveProgress(progress)
    }

    suspend fun toggleEnemy(userId: Int, enemyId: Int, isAdd: Boolean) {
        val current = progressDao.getProgressDirect(userId) ?: Progress(userId = userId)
        val currentSet = current.found_enemies.toIdSet().toMutableSet()
        if (isAdd) {
            currentSet.add(enemyId)
        } else {
            currentSet.remove(enemyId)
        }
        val newSetString = currentSet.toIdString()
        val updated = current.copy(
            found_enemies = newSetString,
            quantity_enemies = currentSet.size
        )
        progressDao.saveProgress(updated)
    }

    suspend fun toggleFood(userId: Int, foodId: Int, isAdd: Boolean) {
        val current = progressDao.getProgressDirect(userId) ?: Progress(userId = userId)
        val currentSet = current.found_foods.toIdSet().toMutableSet()
        if (isAdd) {
            currentSet.add(foodId)
        } else {
            currentSet.remove(foodId)
        }
        val newSetString = currentSet.toIdString()
        val updated = current.copy(
            found_foods = newSetString,
            quantity_foods = currentSet.size
        )
        progressDao.saveProgress(updated)
    }

    suspend fun toggleEquipment(userId: Int, equipmentId: Int, isAdd: Boolean) {
        val current = progressDao.getProgressDirect(userId) ?: Progress(userId = userId)
        val currentSet = current.found_equipment.toIdSet().toMutableSet()
        if (isAdd) {
            currentSet.add(equipmentId)
        } else {
            currentSet.remove(equipmentId)
        }
        val newSetString = currentSet.toIdString()
        val updated = current.copy(
            found_equipment = newSetString,
            quantity_equipment = currentSet.size
        )
        progressDao.saveProgress(updated)
    }
}
