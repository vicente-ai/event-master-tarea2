package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventmaster.data.model.Event

@Composable
fun EventDetailScreen(event: Event?, modifier: Modifier = Modifier) {
    if (event == null) {
        Text(text = "Event not found", modifier = modifier.padding(16.dp))
        return
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = event.nombre, style = MaterialTheme.typography.headlineMedium)
        Text(text = "Tipo: ${event.tipo}", style = MaterialTheme.typography.labelLarge)
        Text(text = "Categoría: ${event.categoria}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Fecha: ${event.fecha}", style = MaterialTheme.typography.bodyMedium)
    }
}
