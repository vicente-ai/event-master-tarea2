package com.example.eventmaster.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventmaster.R
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.ui.theme.EventPurple
import com.example.eventmaster.ui.theme.OnPrimaryColor

@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = EventPurple)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = event.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    color = OnPrimaryColor
                )
                Text(
                    text = event.fecha,
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnPrimaryColor
                )
                Text(
                    text = stringResource(R.string.event_info_format, event.tipo, event.categoria),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnPrimaryColor
                )
            }
        }
    }
}
