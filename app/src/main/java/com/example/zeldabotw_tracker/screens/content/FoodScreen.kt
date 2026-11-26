package com.example.zeldabotw_tracker.screens.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zeldabotw_tracker.R
import com.example.zeldabotw_tracker.components.cards.FoodCard
import com.example.zeldabotw_tracker.components.cards.ProgressCard
import com.example.zeldabotw_tracker.data.api.model.Food
import com.example.zeldabotw_tracker.data.api.remote.RetrofitInstance
import com.example.zeldabotw_tracker.data.api.repository.UiState
import com.example.zeldabotw_tracker.data.api.repository.ZeldaRepository
import com.example.zeldabotw_tracker.data.database.progress.ProgressViewModel
import com.example.zeldabotw_tracker.viewmodel.foods.FoodsViewModel
import com.example.zeldabotw_tracker.viewmodel.foods.FoodsViewModelFactory

@Composable
fun FoodScreen(
    state: UiState<List<Food>>,
    onRetry: () -> Unit,
    userId: Int,
    progressViewModel: ProgressViewModel
) {
    when (state) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = state.message, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRetry) {
                        Text(stringResource(R.string.retry_button))
                    }
                }
            }
        }
        is UiState.Success -> {
            if (state.data.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.empty_foods))
                }
            } else {
                LazyColumn {
                    item {
                        Text(
                            stringResource(R.string.title_foods),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 12.dp, 12.dp, 12.dp),
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item{
                        ProgressCard(
                            userId = userId,
                            typeCard = 1,
                            progressViewModel = progressViewModel,
                            total = 36f,
                            title = stringResource(R.string.title_foods_found)
                        )
                    }
                    items(state.data){food ->
                        FoodCard(food = food, userId = userId, progressViewModel = progressViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun FoodView(
    id: Int,
    progressViewModel: ProgressViewModel
){
    val repository = ZeldaRepository(api = RetrofitInstance.api)
    val factory= remember {
        FoodsViewModelFactory(repository)
    }
    val viewModel: FoodsViewModel = viewModel(factory = factory)
    val state = viewModel.state.collectAsStateWithLifecycle().value

    FoodScreen(
        state = state,
        onRetry = { viewModel.loadFoods() },
        userId = id,
        progressViewModel = progressViewModel
    )
}
