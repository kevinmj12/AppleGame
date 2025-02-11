package com.example.applegame.screen.game_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applegame.AppleGameScreen
import kotlinx.coroutines.delay

@Composable
fun Timer(
    columns: Int,
    itemSize: Dp,
    gameOver: MutableState<Boolean>,
    score: MutableIntState,
    navController: NavController
) {
    val initialTime = 120
    var timeLeft by remember { mutableStateOf(initialTime) }  // 초기 타이머 값 설정
    val animationProgress = remember { Animatable(0f) }  // 애니메이션 진행 상태

    // 타이머가 종료된 후 Game Over 다이얼로그 띄우기
    LaunchedEffect(timeLeft) {
        if (timeLeft < 0) {
            gameOver.value = true
        }
    }

    // 타이머 애니메이션 업데이트
    LaunchedEffect(timeLeft) {
        if (timeLeft >= 0) {
            val progress = (initialTime - timeLeft).toFloat() / initialTime
            animationProgress.animateTo(
                progress,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
        }
    }

    // 타이머 감소 (1초마다)
    LaunchedEffect(timeLeft) {
        if (timeLeft >= 0) {
            delay(1000L)
            timeLeft--
        }
    }

    // Game Over 다이얼로그 처리
    if (gameOver.value) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("게임 오버") },
            text = { Text("스코어: ${score.intValue}") },
            confirmButton = {
                Button(onClick = {
                    // 게임 종료 후 타이머 재설정
                    gameOver.value = false
                    timeLeft = initialTime
                    score.intValue = 0
                }) {
                    Text("재시작")
                }
            },
            dismissButton = {
                Button(onClick = {
                    // 게임 종료 후 타이머 재설정
                    gameOver.value = false
                    navController.navigate(AppleGameScreen.Main.name)
                }) {
                    Text("메인으로")
                }
            },
            modifier = Modifier.padding(16.dp),

        )
    }

    // 타이머 리셋 시 애니메이션을 바로 초기 상태로 설정
    LaunchedEffect(gameOver.value) {
        if (gameOver.value) {
            animationProgress.snapTo(0f) // 애니메이션을 바로 초기 상태로 설정
        }
    }

    // 화면에 표시할 레이아웃
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // 타이머 막대
        Canvas(modifier = Modifier
            .size(width = itemSize * columns - 30.dp , height = 20.dp)
            ) {
            val width = size.width
            val height = size.height

            // 애니메이션 없이 리셋된 상태에서는 바로 꽉 차게
            val barWidth = if (gameOver.value) {
                width // 리셋된 경우에는 애니메이션 없이 바로 꽉 차게
            } else {
                width * animationProgress.value
            }

            // 막대 그리기 (배경)
            drawRect(
                color = Color.Green,
                size = androidx.compose.ui.geometry.Size(width, height)
            )

            // 이동하는 바 그리기
            drawRect(
                color = Color.White,
                size = androidx.compose.ui.geometry.Size(barWidth, height)
            )
        }
        Spacer(modifier = Modifier.width(16.dp)) // Text와 Canvas 사이 간격
        Box(modifier = Modifier.size(30.dp, 20.dp)){
            Text(text = score.intValue.toString())
        }


    }
}



