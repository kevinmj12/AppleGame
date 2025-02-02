package com.example.applegame.screen.game_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.applegame.screen.game_screen.components.Apples
import com.example.applegame.screen.game_screen.components.GameOverDialog
import com.example.applegame.screen.game_screen.components.Timer

@Composable
fun GameScreen() {
    Scaffold (
        containerColor = Color.White, // 배경색을 흰색으로 설정
    ){ innerPadding ->
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

            val screenWidth = maxWidth - (padding * 2) - (imagePadding * (columns - 1))
            val screenHeight = maxHeight - (padding * 2) - (imagePadding * (rows - 1))
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
        }
    }
}
