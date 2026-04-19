package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eventmaster.data.model.Event

@Composable
fun EventDetailScreen(event: Event?, modifier: Modifier = Modifier) {
    if (event == null) {
        Text(text = "Event not found", modifier = modifier.padding(16.dp))
        return
    }

    Column(modifier = modifier) {
        if (event.imageUrl != null) {
            AsyncImage(
                model = event.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Category: ${event.category}", style = MaterialTheme.typography.labelLarge)
            Text(text = event.date, style = MaterialTheme.typography.bodyLarge)
            Text(text = event.location, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
