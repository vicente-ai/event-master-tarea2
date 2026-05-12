package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventmaster.R
import com.example.eventmaster.ui.theme.EventPurple
import com.example.eventmaster.ui.theme.OnPrimaryColor
import com.example.eventmaster.ui.viewmodel.EventViewModel
import com.example.eventmaster.ui.components.BackButton
import com.example.eventmaster.ui.components.TitleBadge
import com.example.eventmaster.ui.components.ValidatedDropdown
import com.example.eventmaster.ui.components.ValidatedTextField

@Composable
fun AddEventScreen(
    viewModel: EventViewModel,
    categoryName: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(viewModel.eventTypes.firstOrNull().orEmpty()) }
    var nombreError by remember { mutableStateOf(false) }
    var fechaError by remember { mutableStateOf(false) }
    var typeError by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                BackButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TitleBadge(text = stringResource(R.string.new_event_title), fontSize = 20)

            Spacer(modifier = Modifier.height(24.dp))

            ValidatedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = false
                },
                labelRes = R.string.event_name_label,
                isError = nombreError,
                errorRes = R.string.error_min_length_name,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = categoryName,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.event_categoria)) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            ValidatedDropdown(
                value = selectedType,
                onValueChange = {
                    selectedType = it
                    typeError = false
                },
                labelRes = R.string.event_tipo,
                options = viewModel.eventTypes,
                expanded = expanded,
                onExpandedChange = { expanded = it },
                isError = typeError,
                errorRes = R.string.error_select_type,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = fecha,
                onValueChange = {
                    fecha = it
                    fechaError = false
                },
                labelRes = R.string.event_date_hint,
                isError = fechaError,
                errorRes = R.string.error_min_length_date,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val isNombreValid = nombre.isNotBlank() && nombre.length >= 2
                    val isFechaValid = fecha.isNotBlank() && fecha.length >= 5
                    val isTypeValid = selectedType.isNotBlank()

                    nombreError = !isNombreValid
                    fechaError = !isFechaValid
                    typeError = !isTypeValid

                    if (isNombreValid && isFechaValid && isTypeValid) {
                        viewModel.addEvent(nombre, categoryName, selectedType, fecha)
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EventPurple,
                    contentColor = OnPrimaryColor
                )
            ) {
                Text(stringResource(R.string.save_event_button))
            }
        }
    }
}