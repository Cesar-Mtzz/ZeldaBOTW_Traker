package com.example.zeldabotw_tracker.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zeldabotw_tracker.R
import com.example.zeldabotw_tracker.components.RemoteImage
import com.example.zeldabotw_tracker.data.api.model.Monster
import com.example.zeldabotw_tracker.data.database.progress.ProgressViewModel
import com.example.zeldabotw_tracker.data.database.progress.toIdSet

@Composable
fun MonsterCard(
    monster: Monster,
    userId: Int,
    progressViewModel: ProgressViewModel
) {
    val progress by progressViewModel.getProgress(userId).collectAsState()
    val monsterId = monster.id ?: 0
    val isFound = progress?.found_enemies?.toIdSet()?.contains(monsterId) == true

    val noDataStr = stringResource(R.string.no_data)

    Column(
        modifier = Modifier.padding(8.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RemoteImage(
                        imageUrl = monster.image,
                        contentDescription = monster.name,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = monster.name ?: noDataStr,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = if (monster.dlc == true) {
                                stringResource(R.string.dlc_yes)
                            } else {
                                stringResource(R.string.dlc_no)
                            }
                        )

                        Text(
                            text = stringResource(
                                R.string.label_common_locations,
                                monster.common_locations?.takeIf { it.isNotEmpty() }
                                    ?.joinToString(", ") ?: noDataStr
                            )
                        )

                        Text(
                            text = stringResource(
                                R.string.label_drops,
                                monster.drops?.takeIf { it.isNotEmpty() }
                                    ?.joinToString(", ") ?: noDataStr
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            progressViewModel.toggleEnemy(userId = userId, enemyId = monsterId, isAdd = true)
                        },
                        enabled = !isFound,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.btn_add))
                    }

                    Button(
                        onClick = {
                            progressViewModel.toggleEnemy(userId = userId, enemyId = monsterId, isAdd = false)
                        },
                        enabled = isFound,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.btn_remove))
                    }
                }
            }
        }
    }
}
