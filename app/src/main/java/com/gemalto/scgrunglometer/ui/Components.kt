package com.gemalto.scgrunglometer.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Symptom
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme

@Composable
fun IngredientDisplay(ingredient: Ingredient, modifier: Modifier = Modifier) {
    Text(
        text = ingredient.name,
        modifier = modifier.padding(vertical = 10.dp)
    )
}

@Composable
fun SymptomDisplay(symptom: Symptom) {
    Text(
        text = symptom.name,
        modifier = Modifier.padding(vertical = 10.dp)
    )
}

@Preview
@Composable
fun IngredientDisplayPreview() {
    ScrunglometerTheme {
        IngredientDisplay(Ingredient("Parsley"))
    }
}