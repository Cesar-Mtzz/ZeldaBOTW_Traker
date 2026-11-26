package com.example.zeldabotw_tracker.screens.userviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zeldabotw_tracker.components.BottomBar
import com.example.zeldabotw_tracker.data.database.progress.ProgressViewModel
import com.example.zeldabotw_tracker.screens.content.EquipmentView
import com.example.zeldabotw_tracker.screens.content.FoodView
import com.example.zeldabotw_tracker.screens.content.MonsterView
import com.example.zeldabotw_tracker.screens.content.OverallProgressView

@Composable
fun HomeView(
    userId: Int,
    progressViewModel: ProgressViewModel = viewModel()
) {
    val id: Int by remember { mutableIntStateOf(userId) }
    var selectedItem: Int by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedItem = selectedItem,
                onItemSelected = {
                    selectedItem = it
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedItem) {
                0 -> MonsterView(id, progressViewModel = progressViewModel)
                1 -> FoodView(id, progressViewModel = progressViewModel)
                2 -> EquipmentView(id, progressViewModel = progressViewModel)
                3 -> OverallProgressView(id, progressViewModel = progressViewModel)
            }
        }
    }
}
