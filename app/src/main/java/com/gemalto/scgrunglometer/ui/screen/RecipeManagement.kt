package com.gemalto.scgrunglometer.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gemalto.scgrunglometer.R
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Recipe
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme
import com.gemalto.scgrunglometer.ui.theme.Typography

@Composable
fun RecipeManagementScreen(recipe: Recipe, ingredients: List<Ingredient>, onAddIngredient: (Ingredient) -> Unit,
                           onRemoveIngredient: (Ingredient) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "${recipe.name} Ingredients",
            style = Typography.titleLarge
        )
        ingredients.forEach {
            Log.d("Eric","Ingredient ${it.name}")
            recipe.ingredients.forEach { alt ->
                Log.d("Eric","${alt.name} equals ${it == alt}")
            }

            Row {
                Text(
                    text = it.name,
                    modifier = Modifier.weight(.8f)
                )
                if (recipe.ingredients.contains(it)) {
                    Button(
                        onClick = {
                            onRemoveIngredient(it)
                        },
                        colors = ButtonDefaults.textButtonColors().copy(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text(stringResource(R.string.remove))
                    }
                } else {
                    Button(
                        onClick = {
                            onAddIngredient(it)
                        }
                    ) {
                        Text(stringResource(R.string.add))
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun RecipeManagementScreenPreview() {

    val onion = Ingredient("Onion")
    val broth = Ingredient("Beef Broth")
    val noodles = Ingredient("Rice Noodles")

    val recipe = Recipe("Pho")
    recipe.ingredients.add(broth)
    recipe.ingredients.add(onion)

    val ingredients = listOf(onion, broth, noodles)

    ScrunglometerTheme {
        RecipeManagementScreen(recipe, ingredients, {}, {})
    }
}