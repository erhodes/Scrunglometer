package com.gemalto.scgrunglometer.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gemalto.scgrunglometer.R
import com.gemalto.scgrunglometer.model.Symptom
import com.gemalto.scgrunglometer.ui.SymptomDisplay
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme
import com.gemalto.scgrunglometer.ui.theme.Typography

@Composable
fun SymptomsScreen(symptoms: List<Symptom>, onAdd: (String) -> Unit, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(R.string.symptoms),
            style = Typography.titleLarge
        )
        symptoms.forEach {
            SymptomDisplay(it)
        }

        var newSymptomName by rememberSaveable { mutableStateOf("") }
        Row {
            OutlinedTextField(
                value = newSymptomName,
                label = { Text("New Symptom") },
                onValueChange = {
                    newSymptomName = it
                },
                modifier = modifier
            )
            Button(
                onClick = {
                    onAdd(newSymptomName)
                }
            ) {
                Text("Add")
            }
        }
    }
}

@Preview
@Composable
fun SymptomsListPreview() {
    ScrunglometerTheme {
        val symptoms = listOf(Symptom("Headache"))

        SymptomsScreen(symptoms, {})
    }
}