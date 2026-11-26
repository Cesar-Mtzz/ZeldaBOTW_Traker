package com.example.zeldabotw_tracker.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zeldabotw_tracker.data.database.progress.ProgressViewModel
import com.example.zeldabotw_tracker.data.database.progress.toIdSet

@Composable
fun ProgressCard(
    userId: Int,
    typeCard: Int,
    progressViewModel: ProgressViewModel,
    total: Float,
    title: String
) {
    val progress by progressViewModel.getProgress(userId).collectAsState()

    val count = when (typeCard) {
        0 -> progress?.found_enemies?.toIdSet()?.size?.toFloat() ?: 0f
        1 -> progress?.found_foods?.toIdSet()?.size?.toFloat() ?: 0f
        2 -> progress?.found_equipment?.toIdSet()?.size?.toFloat() ?: 0f
        3 -> {
            val enemies = progress?.found_enemies?.toIdSet()?.size?.toFloat() ?: 0f
            val foods = progress?.found_foods?.toIdSet()?.size?.toFloat() ?: 0f
            val equipment = progress?.found_equipment?.toIdSet()?.size?.toFloat() ?: 0f
            enemies + foods + equipment
        }
        else -> 0f
    }

    val progressFraction = if (total > 0f) {
        (count / total).coerceIn(0f, 1f)
    } else 0f

    val percentageInt = (progressFraction * 100).toInt()

    Column(
        modifier = Modifier.padding(8.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${count.toInt()} / ${total.toInt()} ($percentageInt%)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            }
        }
    }
}
