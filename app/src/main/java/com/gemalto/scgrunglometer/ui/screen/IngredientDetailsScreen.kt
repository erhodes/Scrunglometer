package com.gemalto.scgrunglometer.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gemalto.scgrunglometer.R
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Symptom
import com.gemalto.scgrunglometer.ui.SymptomDisplay
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme

@Composable
fun IngredientDetailsScreen(ingredient: Ingredient, onNavigate: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = ingredient.name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.associated_symptoms),
            style = MaterialTheme.typography.titleMedium
        )
        HorizontalDivider()
        ingredient.associatedSymptoms.forEach {
            SymptomDisplay(it)
        }
        Button(
            onClick = onNavigate
        ) {
            Text(stringResource(R.string.manage_symptoms))
        }
    }
}

@Preview
@Composable
fun IngredientDetailsScreenPreview() {
    val ingredient = Ingredient("Noodles")
    ingredient.addSymptom(Symptom("Headache"))

    ScrunglometerTheme {
        IngredientDetailsScreen(ingredient, {})
    }
}