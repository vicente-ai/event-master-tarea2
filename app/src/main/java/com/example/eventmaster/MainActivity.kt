package com.example.eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.data.model.Category
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.ui.navigation.EventMasterNavGraph
import com.example.eventmaster.ui.navigation.Screen
import com.example.eventmaster.ui.theme.EventMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventMasterTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

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
                    mutableStateOf(
                        listOf(
                            Event(1, "Concierto Rock", "conciertos", "Música", "2024-12-01")
                        )
                    )
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        if (currentRoute == Screen.EventList.route) {
                            Column {
                                FloatingActionButton(
                                    onClick = { navController.navigate(Screen.AddCategory.route) },
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Add Category")
                                }
                                FloatingActionButton(
                                    onClick = { navController.navigate(Screen.AddEvent.route) }
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add Event")
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    EventMasterNavGraph(
                        navController = navController,
                        categories = categories,
                        events = events,
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
