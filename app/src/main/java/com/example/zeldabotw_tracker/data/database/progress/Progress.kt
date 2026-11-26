package com.example.zeldabotw_tracker.data.database.progress

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class Progress(
    @PrimaryKey
    val userId: Int,
    val quantity_enemies: Int = 0,
    val quantity_foods: Int = 0,
    val quantity_equipment: Int = 0,
    val found_enemies: String = "",
    val found_foods: String = "",
    val found_equipment: String = ""
)

fun String.toIdSet(): Set<Int> {
    if (this.isBlank()) return emptySet()
    return this.split(",")
        .mapNotNull { it.trim().toIntOrNull() }
        .toSet()
}

fun Set<Int>.toIdString(): String {
    return this.joinToString(",")
}
