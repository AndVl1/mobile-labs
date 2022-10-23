package ru.bmstu.common

import androidx.compose.runtime.Composable

expect fun getPlatformName(): String

@Composable
expect fun BackHandler(onBack: () -> Unit)