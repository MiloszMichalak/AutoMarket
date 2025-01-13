package com.menene.automarket.app.presentation.screens.autodetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.presentation.common.ErrorView
import com.menene.automarket.app.presentation.common.LoadingIndicator
import com.menene.automarket.app.presentation.model.UiState
import com.menene.automarket.app.presentation.screens.AutoViewModel

@Composable
fun AutoDetailScreen(
    autoViewModel: AutoViewModel = hiltViewModel(),
    autoId: Int
) {
    val autoState by autoViewModel.autoUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        autoViewModel.getAuto(autoId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (autoState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> AutoDetail((autoState as UiState.Success).data)
            is UiState.Error -> ErrorView((autoState as UiState.Error).message)
        }
    }
}

@Composable
fun AutoDetail(auto: Auto) {
    Column {
        Text(text = auto.id)
        Text(text = auto.brand)
        Text(text = auto.model)
        Text(text = auto.year)
        Text(text = auto.price)
        auto.photos.forEach {
            AsyncImage(
                model = it.url,
                contentDescription = null,
            )
        }
    }
}