package com.example.applegame.screen.game_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.applegame.R

@Composable
fun GameScreen() {
    val padding = 16.dp
    val imagePadding = 6.dp

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 텍스트 표시 영역
            Box {
                Text(text = "타이머 들어올 예정")
            }
            // 이미지 그리드 영역
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ) {
                val columns = 16
                val rows = 9

                val screenWidth = maxWidth - (padding * 2) - (imagePadding * (columns - 1))
                val screenHeight = maxHeight - (padding * 2) - (imagePadding * (rows - 1))

                val itemSize: Dp = minOf(
                    screenWidth / columns,
                    screenHeight / rows
                ) // 셀 간의 여백을 위한 비율 조정

                Column(
                    verticalArrangement = Arrangement.spacedBy(imagePadding)
                ) {
                    repeat(rows) { // 10개의 행
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(imagePadding)
                        ) {
                            repeat(columns) { // 17개의 열
                                Image(
                                    painter = painterResource(id = R.drawable.apple),
                                    contentDescription = null,
                                    modifier = Modifier.size(itemSize) // 원하는 이미지 크기
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
