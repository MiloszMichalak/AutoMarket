package com.menene.automarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.menene.automarket.presentation.AutoViewModel
import com.menene.automarket.ui.theme.AutoMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<AutoViewModel>()
            val autos by viewModel.autos.collectAsStateWithLifecycle()

            AutoMarketTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LaunchedEffect(Unit) {
                        viewModel.getAutos()
                    }
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(text = "Hello, World!")

                        LazyColumn {
                            items(autos) { auto ->
                                Text(text = auto.id)
                                Text(text = auto.brand)
                                Text(text = auto.model)
                                Text(text = auto.year.toString())
                                Text(text = auto.price.toString())
                                AsyncImage(
                                    model = auto.url,
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}