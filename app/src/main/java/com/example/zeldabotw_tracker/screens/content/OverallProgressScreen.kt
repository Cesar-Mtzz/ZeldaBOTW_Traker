package com.example.zeldabotw_tracker.screens.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zeldabotw_tracker.R
import com.example.zeldabotw_tracker.components.cards.ProgressCard
import com.example.zeldabotw_tracker.data.database.progress.ProgressViewModel

@Composable
fun OverallProgressView(
    userId: Int,
    progressViewModel: ProgressViewModel
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                stringResource(R.string.title_overall_progress),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 12.dp, 12.dp, 4.dp),
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            ProgressCard(
                userId = userId,
                typeCard = 3,
                progressViewModel = progressViewModel,
                total = 291f,
                title = stringResource(R.string.title_total_progress)
            )
        }
        item {
            ProgressCard(
                userId = userId,
                typeCard = 0,
                progressViewModel = progressViewModel,
                total = 81f,
                title = stringResource(R.string.title_enemies_eliminated)
            )
        }
        item {
            ProgressCard(
                userId = userId,
                typeCard = 1,
                progressViewModel = progressViewModel,
                total = 36f,
                title = stringResource(R.string.title_foods_found)
            )
        }
        item {
            ProgressCard(
                userId = userId,
                typeCard = 2,
                progressViewModel = progressViewModel,
                total = 174f,
                title = stringResource(R.string.title_equipment_found)
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
