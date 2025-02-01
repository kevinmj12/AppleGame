package com.example.applegame.screen.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applegame.AppleGameScreen

@Composable
fun MainScreen(
    navController: NavController,
){
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ){
            Text(text="Main Screen")
            Button(onClick = {navController.navigate(AppleGameScreen.Game.name)}) {
                Text(text="Go to Game Screen")
            }
            Button(onClick = {navController.navigate(AppleGameScreen.Score.name)}) {
                Text(text="Go to Score Screen")
            }
        }

    }

}