package com.example.applegame.screen.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.applegame.R

@Composable
fun Apple(
    itemSize: Dp
){
    Image(
        painter = painterResource(id = R.drawable.apple),
        contentDescription = null,
        modifier = Modifier.size(itemSize)
    )
}