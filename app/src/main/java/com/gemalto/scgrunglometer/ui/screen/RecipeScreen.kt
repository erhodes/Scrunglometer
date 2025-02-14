package com.gemalto.scgrunglometer.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Recipe
import com.gemalto.scgrunglometer.ui.IngredientDisplay
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme

@Composable
fun RecipeScreen(recipe: Recipe, onNavigateToDetails: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
//        var newIngredient by rememberSaveable { mutableStateOf("") }
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.titleLarge
        )
        HorizontalDivider()
        recipe.ingredients.forEach {
            IngredientDisplay(it)
        }

        Button(
            onClick = onNavigateToDetails
        ) {
            Text(stringResource(R.string.manage_ingredients))
        }

//        ingredients.forEach {
//            Row {
//                Text(it.name)
//                Button(
//                    onClick = {
//                        onAddIngredient(it)
//                    }
//                ) {
//                    Text("Add")
//                }
//            }
//        }

//        Row {
//            OutlinedTextField(
//                value = newIngredient,
//                label = { Text("New Ingredient") },
//                onValueChange = {
//                    newIngredient = it
//                },
//                modifier = modifier
//            )
//            Button(
//                onClick = {
//                    onAddIngredient(Ingredient(newIngredient))
//                }
//            ) {
//                Text("Add")
//            }
//        }

    }
}

@Preview
@Composable
fun RecipeScreenPreview() {
    ScrunglometerTheme {
        val recipe = Recipe("Pho")
        recipe.ingredients.add(Ingredient("Beef Broth"))
        recipe.ingredients.add(Ingredient("Onion"))

        RecipeScreen(recipe, {})
    }
}