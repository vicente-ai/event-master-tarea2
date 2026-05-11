package com.example.eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.navigation.EventMasterNavGraph
import com.example.eventmaster.ui.theme.EventMasterTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventMasterTheme {
                EventMasterMainScreen()
            }
        }
    }
}

@Composable
fun EventMasterMainScreen() {
    val navController = rememberNavController()

    EventMasterNavGraph(navController = navController)
}