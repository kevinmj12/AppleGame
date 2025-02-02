package com.example.applegame.screen.game_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameOverDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Game Over") },
        text = { Text("Your game has ended!") },
        confirmButton = {
            Button(onClick = {}) {
                Text("OK")
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}