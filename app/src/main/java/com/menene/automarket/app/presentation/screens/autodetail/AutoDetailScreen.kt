package com.menene.automarket.app.presentation.screens.autodetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.presentation.screens.components.AutoPager
import com.menene.automarket.app.presentation.screens.components.PageIndicator

@Composable
fun AutoDetailScreen(
    autoId: Int
) {
    val autoViewModel: AutoDetailViewModel = hiltViewModel()
    val auto by autoViewModel.getAuto(autoId).collectAsStateWithLifecycle(initialValue = null)

    val listCount = auto?.photos?.size ?: 0
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = { listCount }
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
        ){
            AutoPager(
                photos = auto?.photos ?: emptyList(),
                pagerState = pagerState,
            )
        }
        PageIndicator(
            size = listCount,
            pagerState = pagerState,
            coroutineScope = coroutineScope,
        )
    }

}