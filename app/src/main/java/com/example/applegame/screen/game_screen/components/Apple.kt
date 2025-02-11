package com.example.applegame.screen.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.applegame.R

data class AppleItem(
    val id: Int,
    val position: Offset,
    val size: Dp,
    var isSelected: Boolean = false
)

@Composable
fun Apple(
    itemSize: Dp,
    isSelected: Boolean,
    isRemoved: Boolean,
    value: Int
) {
    val imageRes = if (isSelected) R.drawable.apple_selected else R.drawable.apple

    Box(modifier = Modifier.size(itemSize)) {
        if (!isRemoved){
            Box{
                // 이미지 배경
                Image(
                    painter = painterResource(id = imageRes), // 이미지 리소스
                    contentDescription = "apple",
                )

                // 숫자 텍스트
                Text(
                    text = value.toString(), // 표시할 숫자
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
}

