package com.example.applegame.screen.game_screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.applegame.screen.game_screen.components.Apples
import com.example.applegame.screen.game_screen.components.Timer
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times
import kotlin.random.Random

@Composable
fun GameScreen() {
    Scaffold(containerColor = Color.White) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val columns = 16
            val rows = 9
            val padding: Dp = 16.dp

            val screenWidth = maxWidth - (padding * 2)
            val screenHeight = maxHeight - (padding * 4)
            val itemSize: Dp = minOf(screenWidth / columns, screenHeight / rows)

            val appleStartY = (padding * 2) + (itemSize/2)
            val appleStartX = (maxWidth - (itemSize * columns))/2+(itemSize/2)

            val appleValues = remember { mutableStateListOf<Int>() }
            val selectedApples = remember { mutableStateListOf<Int>() }
            val removedApples = remember { mutableStateListOf<Int>() }
            val gameOver = remember { mutableStateOf(false) }

            for (i: Int in 1..columns*rows){
                appleValues.add(Random.nextInt(1, 10))
            }

            Column {
                Timer(columns = columns, itemSize = itemSize, gameOver = gameOver)
                Apples(columns = columns, rows = rows, itemSize = itemSize,
                    appleValues = appleValues,
                    selectedApples = selectedApples,
                    removedApples = removedApples
                )
            }

            var startOffset by remember { mutableStateOf<Offset?>(null) }
            var endOffset by remember { mutableStateOf<Offset?>(null) }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val down = awaitFirstDown()

                                val startXDp = down.position.x.toDp()
                                val startYDp = down.position.y.toDp()

                                // 픽셀(px)로 변환하여 startOffset 설정
                                startOffset = Offset(startXDp.toPx(), startYDp.toPx())
                                endOffset = startOffset // 초기화

                                selectedApples.clear() // 드래그 시작 시 초기화

                                var isDragging = true
                                while (isDragging) {
                                    val event = awaitPointerEvent()
                                    event.changes.forEach { change ->
                                        if (change.pressed) {
                                            endOffset = change.position

                                            // 드래그 중 선택된 사과 실시간 업데이트
                                            val left = min(startOffset!!.x, endOffset!!.x).toDp()
                                            val right = max(startOffset!!.x, endOffset!!.x).toDp()
                                            val top = min(startOffset!!.y, endOffset!!.y).toDp()
                                            val bottom = max(startOffset!!.y, endOffset!!.y).toDp()

                                            selectedApples.clear()

                                            for (row in 0 until rows) {
                                                for (col in 0 until columns) {
                                                    val appleId = row * columns + col

                                                    val appleY = appleStartY + (row * (itemSize))
                                                    val appleX = appleStartX + (col * (itemSize))

                                                    if (appleX in left..right && appleY in top..bottom) {
                                                        selectedApples.add(appleId)
                                                    }
                                                }
                                            }
                                        } else {
                                            startOffset = null
                                            endOffset = null
                                            isDragging = false
                                            // 영역 내의 사과 합을 구해 10이 되면 제거
                                            val sumAppleValues = selectedApples.sumOf { appleValues[it] }
                                            if (sumAppleValues == 10) {
                                                removedApples.addAll(selectedApples)
                                            }
                                            selectedApples.clear()
                                        }
                                    }
                                }
                            }
                        }
                    }
            ) {
                startOffset?.let { start ->
                    endOffset?.let { end ->
                        val topLeft = Offset(min(start.x, end.x), min(start.y, end.y))
                        val size = Size(abs(end.x - start.x), abs(end.y - start.y))

                        drawRect(
                            color = Color.Blue.copy(alpha = 0.4f),
                            topLeft = topLeft,
                            size = size
                        )
                    }
                }
            }
        }
    }
}