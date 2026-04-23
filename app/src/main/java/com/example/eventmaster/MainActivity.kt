package com.example.eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.navigation.EventMasterNavGraph
import com.example.eventmaster.ui.theme.EventMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventMasterTheme {
                EventMasterApp()
            }
        }
    }
}

@Composable
fun EventMasterApp() {
    val navController = rememberNavController()

    EventMasterNavGraph(navController = navController)
}