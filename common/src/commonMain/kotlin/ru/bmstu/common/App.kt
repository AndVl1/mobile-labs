package ru.bmstu.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ru.bmstu.common.lab1.BottleScreen

@Composable
fun App() {
    val currentState = remember { mutableStateOf(LabsState.SELECTION) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentState.value.title) },
                navigationIcon = {
                    when (currentState.value) {
                        LabsState.SELECTION -> {}
                        else -> {
                            Icon(
                                modifier = Modifier.clickable { currentState.value = LabsState.SELECTION },
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        }
    ) {
        Crossfade(currentState.value) {
            when (it) {
                LabsState.SELECTION -> SelectScreen { selected -> currentState.value = selected }
                LabsState.LAB_1 -> BottleScreen()
            }
        }
    }
    BackHandler {
        when (currentState.value) {
            LabsState.LAB_1 -> currentState.value = LabsState.SELECTION
            else -> {}
        }
    }
}
