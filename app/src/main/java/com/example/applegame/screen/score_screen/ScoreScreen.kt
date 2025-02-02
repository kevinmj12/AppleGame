package com.example.applegame.screen.score_screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.applegame.screen.game_screen.components.Apples

@Composable
fun ScoreScreen(){
    Scaffold { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            val padding = 16.dp
            val imagePadding = 8.dp

            val columns = 16
            val rows = 9

            val screenWidth = maxWidth - (padding * 2) - (imagePadding * (columns - 1))
            val screenHeight = maxHeight - (padding * 4) - (imagePadding * (rows - 1))

            val itemSize: Dp = minOf(
                screenWidth / columns,
                screenHeight / rows
            )

            Column{
                Text(text="123")

            }
        }
    }

}