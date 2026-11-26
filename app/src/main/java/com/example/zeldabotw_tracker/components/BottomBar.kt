package com.example.zeldabotw_tracker.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.zeldabotw_tracker.R

data class BottomItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun BottomBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
){
    val items = listOf(
        BottomItem(
            title = stringResource(R.string.nav_enemies),
            icon = Icons.Default.Dangerous
        ),
        BottomItem(
            title = stringResource(R.string.nav_foods),
            icon = Icons.Default.Fastfood
        ),
        BottomItem(
            title = stringResource(R.string.nav_equipment),
            icon = Icons.Default.Shield
        ),
        BottomItem(
            title = stringResource(R.string.nav_progress),
            icon = Icons.Default.BarChart
        )
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}
