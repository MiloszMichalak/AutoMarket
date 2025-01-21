package com.menene.automarket.app.presentation.screens.autodetail

import androidx.compose.foundation.layout.Column
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

    Column(
        Modifier.height(300.dp)
    ) {
        auto?.let { AutoDetail(it) }
    }
}

@Composable
fun AutoDetail(auto: Auto) {
    val listCount = auto.photos.size
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = { listCount }
    )
    AutoPager(
        photos = auto.photos,
        pagerState = pagerState,
    )
    PageIndicator(
        size = listCount,
        pagerState = pagerState,
        coroutineScope = coroutineScope,
    )

}