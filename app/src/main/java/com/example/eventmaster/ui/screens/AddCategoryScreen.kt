package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventmaster.R
import com.example.eventmaster.ui.components.BackButton
import com.example.eventmaster.ui.components.TitleBadge
import com.example.eventmaster.ui.components.ValidatedTextField
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun AddCategoryScreen(
    viewModel: EventViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var nombreError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }

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

            TitleBadge(text = stringResource(R.string.new_category_title), fontSize = 20)

            Spacer(modifier = Modifier.height(24.dp))

            ValidatedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = false
                },
                labelRes = R.string.category_name_label,
                isError = nombreError,
                errorRes = R.string.error_min_length_name,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ValidatedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                    descripcionError = false
                },
                labelRes = R.string.category_description_label,
                isError = descripcionError,
                errorRes = R.string.error_min_length_description,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val isNombreValid = nombre.isNotBlank() && nombre.length >= 2
                    val isDescripcionValid = descripcion.isEmpty() || descripcion.length >= 3

                    nombreError = !isNombreValid
                    descripcionError = !isDescripcionValid

                    if (isNombreValid && isDescripcionValid) {
                        viewModel.addCategory(nombre)
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(stringResource(R.string.save_category_button))
            }
        }
    }
}