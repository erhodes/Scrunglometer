package com.gemalto.scgrunglometer.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gemalto.scgrunglometer.R
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme

@Composable
fun HomeScreen(onRecipesClick: () -> Unit, onIngredientsClick: () -> Unit, onSymptomsClick: () -> Unit, modifier: Modifier = Modifier) {
    Column {
        Button(
            onClick = onRecipesClick
        ) {
            Text(stringResource(R.string.recipes))
        }
        Button(
            onClick = onIngredientsClick
        ) {
            Text(stringResource(R.string.ingredients))
        }
        Button(
            onClick = onSymptomsClick
        ) {
            Text(stringResource(R.string.symptoms))
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ScrunglometerTheme {
        HomeScreen({}, {}, {})
    }
}