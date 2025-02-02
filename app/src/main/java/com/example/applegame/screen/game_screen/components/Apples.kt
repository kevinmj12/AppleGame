package com.example.applegame.screen.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.applegame.R

@Composable
fun Apples(
    columns: Int,
    rows: Int,
    padding: Dp,
    imagePadding: Dp,
    itemSize: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top=padding),

        verticalArrangement = Arrangement.spacedBy(imagePadding)
    ) {
        repeat(rows) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(imagePadding),
            ) {
                repeat(columns) {
                    Apple(itemSize = itemSize)
                }
            }
        }
    }
}