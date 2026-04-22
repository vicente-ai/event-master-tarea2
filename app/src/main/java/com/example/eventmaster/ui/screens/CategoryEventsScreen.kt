package com.example.eventmaster.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventmaster.R
import com.example.eventmaster.ui.viewmodel.Event
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun CategoryEventsScreen(
    categoryName: String,
    viewModel: EventViewModel,
    onBack: () -> Unit,
    onAddEventClick: (String) -> Unit
) {
    val events = viewModel.getEventsByCategory(categoryName)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddEventClick(categoryName) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(50)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Event")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onBack,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.align(Alignment.TopStart).height(36.dp)
                ) {
                    Text("Volver", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(
                    text = categoryName,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo Small",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(events) { event ->
                    ExpandableEventItem(event)
                }
            }
        }
    }
}

@Composable
fun ExpandableEventItem(event: Event) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = event.name,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Tipo: ${event.type}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Fecha: ${event.date}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}