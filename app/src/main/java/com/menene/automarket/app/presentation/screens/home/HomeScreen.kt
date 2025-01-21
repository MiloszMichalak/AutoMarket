package com.menene.automarket.app.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.presentation.navigation.Screen
import com.menene.automarket.app.presentation.screens.components.AutoPager
import com.menene.automarket.app.presentation.screens.components.PageIndicator

@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {
    val autoViewModel: AutoViewModel = hiltViewModel()
    val autos by autoViewModel.autos.collectAsStateWithLifecycle()

    Column {
        autos?.let {
            AutoList(
                autos = it,
                navHostController = navHostController,
            )
        }
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
    ) {
        itemsIndexed(
            items = autos,
        ) { index, auto ->
            AutoItem(
                auto = auto,
                key = index,
                navHostController = navHostController,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AutoItem(
    auto: Auto,
    key: Int,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val autoSize = auto.photos.size
    val pagerState = rememberPagerState(pageCount = {
        autoSize
    })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                navHostController.navigate(Screen.AutoDetail(autoId = key))
            }
    ) {
        Box {
            AutoPager(
                photos = auto.photos,
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
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
        PageIndicator(
            size = autoSize,
            pagerState = pagerState,
            coroutineScope = coroutineScope,
        )
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