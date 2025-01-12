package com.menene.automarket.app.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.presentation.model.UiState
import com.menene.automarket.app.presentation.navigation.Screen

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
                navHostController
            )
            is UiState.Error -> ErrorView((state as UiState.Error).message)
        }
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(
    error: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: $error",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun AutoList(
    autos: List<Auto>,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            items = autos,
            key = { it.id }
        ) { auto ->
            AutoItem(auto = auto, navHostController = navHostController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AutoItem(
    auto: Auto,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = auto.id)
        Text(text = auto.brand)
        Text(text = auto.model)
        Text(text = auto.year)
        Text(text = auto.price)
        AsyncImage(
            model = auto.url,
            contentDescription = null,
        )
    }
    Button(
        onClick = {
            navHostController.navigate(Screen.AutoDetail(autoId = auto.id.toInt()))
        }
    ) {
        Text(text = "More info")
    }
}