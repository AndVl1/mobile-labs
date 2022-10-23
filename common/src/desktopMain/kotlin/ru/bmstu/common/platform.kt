package ru.bmstu.common

import androidx.compose.runtime.Composable

actual fun getPlatformName(): String {
    return "Desktop"
}

@Composable
actual fun BackHandler(onBack: () -> Unit) {}