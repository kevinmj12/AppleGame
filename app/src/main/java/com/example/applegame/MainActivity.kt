package com.example.applegame

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applegame.screen.game_screen.GameScreen
import com.example.applegame.screen.main_screen.MainScreen
import com.example.applegame.screen.score_screen.ScoreScreen
import com.example.applegame.ui.theme.AppleGameTheme

enum class AppleGameScreen(){
    Main,
    Game,
    Score
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 화면 가로 모드 설정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // 화면 몰입 모드 설정
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // 상태 표시줄, 내비게이션 바 숨김
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        // 스와이프하여 잠깐 보이도록 설정
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            AppleGameTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppleGameScreen.Main.name,
                ){
                    composable(route = AppleGameScreen.Main.name){
                        MainScreen(navController = navController)
                    }
                    composable(route = AppleGameScreen.Game.name){
                        GameScreen()
                    }
                    composable(route = AppleGameScreen.Score.name){
                        ScoreScreen()
                    }
                }
            }
        }
    }
}
