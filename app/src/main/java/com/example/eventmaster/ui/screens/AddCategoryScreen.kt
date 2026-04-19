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
import com.example.eventmaster.ui.components.ValidatedTextField

@Composable
fun AddCategoryScreen(
    onCategoryAdded: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    
    var nombreError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.add_category_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ValidatedTextField(
            value = nombre,
            onValueChange = { 
                nombre = it
                nombreError = it.isBlank()
            },
            labelRes = R.string.category_nombre,
            isError = nombreError
        )

        ValidatedTextField(
            value = descripcion,
            onValueChange = { 
                descripcion = it
                descripcionError = it.isBlank()
            },
            labelRes = R.string.category_descripcion,
            isError = descripcionError
        )

        Button(
            onClick = {
                val isNombreValid = nombre.isNotBlank()
                val isDescValid = descripcion.isNotBlank()
                
                nombreError = !isNombreValid
                descripcionError = !isDescValid

                if (isNombreValid && isDescValid) {
                    onCategoryAdded(
                        Category(
                            id = (0..10000).random(),
                            nombre = nombre,
                            descripcion = descripcion
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
