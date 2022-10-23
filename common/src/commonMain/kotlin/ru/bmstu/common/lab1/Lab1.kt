package ru.bmstu.common.lab1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

// Бутылка, имеющая высоту h и диаметр d, с горлышком длины h=3 и
// диаметром d=3, заполненная жидкостью на k процентов
@Composable
fun BottleScreen() {
    val h = remember { mutableStateOf(3) }
    val d = remember { mutableStateOf(3) }
    val k = remember { mutableStateOf(0.15f) }

    Column(modifier = Modifier.fillMaxSize()) {
        Controls(modifier = Modifier.wrapContentHeight(), h, d, k)
        Canvas(modifier = Modifier.fillMaxSize()) {
            // bottleneck
            val center = this.center
            val halfWidth = 20f * d.value
            val topBottleNeck = Path().apply {
                // top neck arc
                arcTo(
                    rect = Rect(
                        left = center.x - 50f,
                        right = center.x + 50f,
                        top = size.height / 10 - 25f,
                        bottom = size.height / 10 + 25f
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 359f,
                    forceMoveTo = false
                )
            }
            drawPath(path = topBottleNeck, color = Color.Black, style = Stroke(width = 5f))
            drawLine(
                color = Color.Black,
                start = Offset(center.x - 50f, size.height / 10),
                end = Offset(center.x - 50f, size.height / 10 + 100f),
                strokeWidth = 5f
            )
            drawLine(
                color = Color.Black,
                start = Offset(center.x + 50f, size.height / 10),
                end = Offset(center.x + 50f, size.height / 10 + 100f),
                strokeWidth = 5f
            )
            // main bottle part
            val top = size.height / 10 + 110f
            val bottom = size.height / 10 + 110f * (h.value.toFloat() / 2)
            drawPath(
                path = Path().apply {
                    arcTo(
                        rect = Rect(
                            left = center.x - halfWidth,
                            right = center.x + halfWidth,
                            top = top - 30f,
                            bottom = top + 30f
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 359f,
                        forceMoveTo = false
                    )
                },
                color = Color.Black,
                style = Stroke(width = 5f)
            )
            drawLine(
                color = Color.Black,
                strokeWidth = 5f,
                start = Offset(x = center.x + halfWidth, y = size.height / 10 + 110f),
                end = Offset(x = center.x + halfWidth, y = bottom)
            )
            drawLine(
                color = Color.Black,
                strokeWidth = 5f,
                start = Offset(x = center.x - halfWidth, y = size.height / 10 + 110f),
                end = Offset(x = center.x - halfWidth, y = bottom)
            )
            drawPath(
                path = Path().apply {
                    arcTo(
                        rect = Rect(
                            left = center.x - halfWidth,
                            right = center.x + halfWidth,
                            top = bottom - 30f,
                            bottom = bottom + 30f
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                },
                color = Color.Black,
                style = Stroke(width = 5f)
            )
//            -------------- water
            val waterColor = Color.Blue.copy(alpha = .5f)
            val waterTop = top + (bottom - top) * (1f - k.value)
            val waterPath = Path().apply {
                arcTo(
                    rect = Rect(
                        left = center.x - halfWidth,
                        right = center.x + halfWidth,
                        top = waterTop - 30f,
                        bottom = waterTop + 30f
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 359f,
                    forceMoveTo = false
                )
                lineTo(x = center.x + halfWidth, y = bottom)
                arcTo(
                    rect = Rect(
                        left = center.x - halfWidth,
                        right = center.x + halfWidth,
                        top = bottom - 30f,
                        bottom = bottom + 30f
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false
                )
                lineTo(center.x - halfWidth, waterTop)
            }
            drawPath(waterPath, waterColor, style = Fill)
        }
    }
}

@Composable
private fun Controls(
    modifier: Modifier,
    h: MutableState<Int>,
    d: MutableState<Int>,
    k: MutableState<Float>
) {
    Column(modifier = modifier.then(Modifier.fillMaxWidth())) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.weight(.5f), text = "Height ${h.value}")
            Slider(modifier = Modifier.weight(.5f), value = h.value.toFloat(), onValueChange = { h.value = it.toInt() }, valueRange = 3f..10f, steps = 6)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.weight(.5f), text = "Diameter ${d.value}")
            Slider(modifier = Modifier.weight(.5f), value = d.value.toFloat(), onValueChange = { d.value = it.toInt() }, valueRange = 3f..10f, steps = 6)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.weight(.5f), text = "Water fill ${k.value * 100}")
            Slider(modifier = Modifier.weight(.5f), value = k.value, onValueChange = { k.value = it }, valueRange = 0f..1f)
        }
    }
}
