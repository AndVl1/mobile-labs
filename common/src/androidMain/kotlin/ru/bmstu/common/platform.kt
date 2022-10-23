package ru.bmstu.common

import androidx.compose.runtime.Composable

actual fun getPlatformName(): String {
    return "Android"
}

@Composable
actual fun BackHandler(onBack: () -> Unit) {
    androidx.activity.compose.BackHandler(onBack = onBack)
}