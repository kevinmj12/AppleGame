package com.example.applegame.screen.game_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applegame.R

@Composable
fun Apple(itemSize: Dp) {
    val imageModifier = Modifier
     .size(itemSize)

    Box(modifier = Modifier.size(itemSize)) {
        Box{
            // 이미지 배경
            Image(
                painter = painterResource(id = R.drawable.apple), // 이미지 리소스
                contentDescription = "Image with number",
                modifier = imageModifier
            )

            // 숫자 텍스트
            Text(
                text = "1", // 표시할 숫자
                color = Color.White, // 텍스트 색상
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ), // 텍스트 스타일
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = itemSize / 15)
            )
        }
    }
}

