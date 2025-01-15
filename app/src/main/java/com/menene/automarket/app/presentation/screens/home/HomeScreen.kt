package com.menene.automarket.app.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.presentation.common.ErrorView
import com.menene.automarket.app.presentation.common.LoadingIndicator
import com.menene.automarket.app.presentation.model.UiState
import com.menene.automarket.app.presentation.navigation.Screen
import com.menene.automarket.app.presentation.screens.AutoViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    autoViewModel: AutoViewModel = hiltViewModel(),
) {
    val state by autoViewModel.uiState.collectAsStateWithLifecycle()

    Column {
        when (state) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> AutoList(
                (state as UiState.Success).data,
                navHostController,
                autoViewModel
            )

            is UiState.Error -> ErrorView((state as UiState.Error).message)
        }
    }
}


@Composable
fun AutoList(
    autos: List<Auto>,
    navHostController: NavHostController,
    autoViewModel: AutoViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            items = autos,
            key = { it.id }
        ) { auto ->
            AutoItem(
                auto = auto,
                navHostController = navHostController,
                autoViewModel = autoViewModel
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AutoItem(
    auto: Auto,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    autoViewModel: AutoViewModel,
) {
    val imageList = auto.photos
    val state = rememberPagerState {
        imageList.size
    }

//    LaunchedEffect(Unit) {
//        state.animateScrollToPage(1)
//    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                autoViewModel.clearAutoUiState()
                navHostController.navigate(Screen.AutoDetail(autoId = auto.id.toInt()))
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = state,
            ) { index ->
                AsyncImage(
                    model = imageList[index],
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
            Text(
                text = auto.price,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                    .padding(2.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row {
            Text(text = auto.brand + " ")
            Text(text = auto.model)
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Rok produkcji: ")
                Text(text = auto.year)
            }
            Column {
                Text("Przebieg: ")
                Text(text = auto.course)
            }
        }
    }
}