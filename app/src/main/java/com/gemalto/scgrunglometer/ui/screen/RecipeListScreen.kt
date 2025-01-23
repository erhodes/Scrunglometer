package com.gemalto.scgrunglometer.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gemalto.scgrunglometer.R
import com.gemalto.scgrunglometer.model.Recipe
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme

@Composable
fun RecipeListScreen(recipes: List<Recipe>, onAdd: (String) -> Unit, onRecipeClicked: (Recipe) -> Unit, modifier: Modifier = Modifier) {
    Column {
        Text(
            "Current Recipes",
            style = MaterialTheme.typography.titleLarge
        )
        recipes.forEach {
            Text(
                text = it.name,
                modifier = Modifier.clickable {
                    onRecipeClicked(it)
                }
            )
        }
        val context = LocalContext.current
        var newRecipeName by rememberSaveable { mutableStateOf("") }
        var errorText by remember { mutableStateOf("") }
        Row {
            OutlinedTextField(
                value = newRecipeName,
                label = { Text("New Recipe") },
                onValueChange = {
                    errorText = ""
                    newRecipeName = it
                },
                isError = errorText.isNotEmpty(),
                supportingText = {
                    if (errorText.isNotEmpty()) {
                        Text(errorText)
                    }
                },
                modifier = modifier
            )
            Button(
                onClick = {
                    if (newRecipeName.isNotEmpty()) {
                        onAdd(newRecipeName)
                        newRecipeName = ""
                    } else {
                        errorText = context.getString(R.string.error_empty)
                    }
                }
            ) {
                Text("Add")
            }
        }
    }
}

@Preview
@Composable
fun RecipeListScreenPreview() {
    ScrunglometerTheme {
        RecipeListScreen(listOf(Recipe("Pho")), {}, {})
    }
}