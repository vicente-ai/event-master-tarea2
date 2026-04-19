package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventmaster.R
import com.example.eventmaster.data.model.Category
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.ui.components.ValidatedDropdown
import com.example.eventmaster.ui.components.ValidatedTextField

@Composable
fun AddEventScreen(
    categories: List<Category>,
    onEventAdded: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    
    val tipos = listOf(
        stringResource(R.string.tipo_concierto),
        stringResource(R.string.tipo_conferencia),
        stringResource(R.string.tipo_taller)
    )
    var tipoExpanded by remember { mutableStateOf(false) }
    var selectedTipo by remember { mutableStateOf("") }

    var categoriaExpanded by remember { mutableStateOf(false) }
    var selectedCategoria by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var fechaError by remember { mutableStateOf(false) }
    var tipoError by remember { mutableStateOf(false) }
    var categoriaError by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.add_event_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ValidatedTextField(
            value = nombre,
            onValueChange = { 
                nombre = it
                nombreError = it.isBlank()
            },
            labelRes = R.string.event_nombre,
            isError = nombreError
        )

        ValidatedDropdown(
            value = selectedTipo,
            onValueChange = { 
                selectedTipo = it
                tipoError = false
            },
            labelRes = R.string.event_tipo,
            options = tipos,
            expanded = tipoExpanded,
            onExpandedChange = { tipoExpanded = it },
            isError = tipoError
        )

        ValidatedDropdown(
            value = selectedCategoria,
            onValueChange = { 
                selectedCategoria = it
                categoriaError = false
            },
            labelRes = R.string.event_categoria,
            options = categories.map { it.nombre },
            expanded = categoriaExpanded,
            onExpandedChange = { categoriaExpanded = it },
            isError = categoriaError
        )

        ValidatedTextField(
            value = fecha,
            onValueChange = { 
                fecha = it
                fechaError = it.isBlank()
            },
            labelRes = R.string.event_fecha,
            isError = fechaError
        )

        Button(
            onClick = {
                val isNombreValid = nombre.isNotBlank()
                val isFechaValid = fecha.isNotBlank()
                val isTipoValid = selectedTipo.isNotBlank()
                val isCatValid = selectedCategoria.isNotBlank()
                
                nombreError = !isNombreValid
                fechaError = !isFechaValid
                tipoError = !isTipoValid
                categoriaError = !isCatValid

                if (isNombreValid && isFechaValid && isTipoValid && isCatValid) {
                    onEventAdded(
                        Event(
                            id = (0..10000).random(),
                            nombre = nombre,
                            tipo = selectedTipo,
                            categoria = selectedCategoria,
                            fecha = fecha
                        )
                    )
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_button))
        }
    }
}
