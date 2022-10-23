package ru.bmstu.common

actual fun getPlatformName(): String {
    return "Android"
}

actual fun BackHandler(onBack: () -> Unit) {}