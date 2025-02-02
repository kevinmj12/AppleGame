package com.example.applegame.screen.game_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.applegame.screen.game_screen.components.Apples
import com.example.applegame.screen.game_screen.components.GameOverDialog
import com.example.applegame.screen.game_screen.components.Timer
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun GameScreen() {
    Scaffold(
        containerColor = Color.White, // 배경색을 흰색으로 설정
    ) { innerPadding ->
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
            val imagePadding: Dp = 6.dp

            val screenWidth = maxWidth - (padding * 2)
            val screenHeight = maxHeight - (padding * 2)
            val itemSize: Dp = minOf(
                screenWidth / columns,
                screenHeight / rows
            )

            val gameOver = remember {
                mutableStateOf(false)
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Timer(columns = columns, imagePadding = imagePadding, itemSize = itemSize, gameOver = gameOver)
                Apples(columns = columns, rows = rows, padding = padding, imagePadding = imagePadding, itemSize = itemSize)
            }
            Box(modifier = Modifier.fillMaxSize()) {
                var offsetX by remember { mutableStateOf(0f) }
                var offsetY by remember { mutableStateOf(0f) }
            }

            var startOffset by remember { mutableStateOf<Offset?>(null) }
            var endOffset by remember { mutableStateOf<Offset?>(null) }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                // 1. 터치 시작 감지
                                val down = awaitFirstDown()
                                startOffset = down.position
                                endOffset = down.position

                                // 2. 드래그 추적
                                var isDragging = true
                                while (isDragging) {
                                    val event = awaitPointerEvent()
                                    event.changes.forEach { change ->
                                        if (change.pressed) {
                                            endOffset = change.position
                                        } else {
                                            // 3. 손가락 뗐을 때 초기화
                                            startOffset = null
                                            endOffset = null
                                            isDragging = false
                                        }
                                    }
                                }
                            }
                        }
                    }
            ) {
                // 4. 사각형 그리기
                startOffset?.let { start ->
                    endOffset?.let { end ->
                        val topLeft = Offset(min(start.x, end.x), min(start.y, end.y))
                        val size = Size(
                            abs(end.x - start.x),
                            abs(end.y - start.y)
                        )

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