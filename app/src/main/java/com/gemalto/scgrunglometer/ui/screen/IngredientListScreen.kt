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
import androidx.compose.ui.tooling.preview.Preview
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.ui.IngredientDisplay
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme

@Composable
fun IngredientListScreen(ingredients: List<Ingredient>, onAdd: (Ingredient) -> Unit, modifier: Modifier = Modifier) {

    Column {
        ingredients.forEach {
            IngredientDisplay(it)
        }

        var newIngredient by rememberSaveable { mutableStateOf("") }
        Row {
            OutlinedTextField(
                value = newIngredient,
                label = { Text("New Ingredient") },
                onValueChange = {
                    newIngredient = it
                },
                modifier = modifier
            )
            Button(
                onClick = {
                    onAdd(Ingredient(newIngredient))
                }
            ) {
                Text("Add")
            }
        }
    }
}

@Preview
@Composable
fun IngredientListScreenPreview() {
    ScrunglometerTheme {
        IngredientListScreen(listOf(Ingredient("Parsley"), Ingredient("Sage")), {})
    }
}