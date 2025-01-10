package com.menene.automarket.app.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(){
    @Serializable
    data object Home : Screen()

    @Serializable
    data class AutoDetail(val autoId: Int) : Screen()
}