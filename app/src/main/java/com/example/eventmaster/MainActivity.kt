package com.example.eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.data.model.Category
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.ui.navigation.EventMasterNavGraph
import com.example.eventmaster.ui.theme.EventMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventMasterTheme {
                val navController = rememberNavController()
                
                var categories by remember {
                    mutableStateOf(
                        listOf(
                            Category(1, "Música", "Eventos musicales"),
                            Category(2, "Deportes", "Competencias físicas"),
                            Category(3, "Tecnología", "Charlas de IT")
                        )
                    )
                }

                var events by remember {
                    mutableStateOf(emptyList<Event>())
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EventMasterNavGraph(
                        navController = navController,
                        categories = categories,
                        onEventAdded = { newEvent ->
                            events = events + newEvent
                        },
                        onCategoryAdded = { newCategory ->
                            categories = categories + newCategory
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
