package com.example.applegame.screen.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Apples(
    columns: Int,
    rows: Int,
    itemSize: Dp,
    appleValues: List<Int>,
    selectedApples: List<Int>,
    removedApples: List<Int>,
) {
    var appleId = 0

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top=16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(rows) {
            Row(
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(columns) {
                    Apple(itemSize = itemSize,
                        isSelected = selectedApples.contains(appleId),
                        isRemoved = removedApples.contains(appleId),
                        value = appleValues[appleId])
                    appleId++
                }
            }
        }
    }
}