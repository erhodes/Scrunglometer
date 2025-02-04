package com.gemalto.scgrunglometer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gemalto.scgrunglometer.ui.Destinations
import com.gemalto.scgrunglometer.ui.screen.HomeScreen
import com.gemalto.scgrunglometer.ui.screen.IngredientListScreen
import com.gemalto.scgrunglometer.ui.screen.RecipeListScreen
import com.gemalto.scgrunglometer.ui.screen.RecipeManagementScreen
import com.gemalto.scgrunglometer.ui.screen.RecipeScreen
import com.gemalto.scgrunglometer.viewmodel.IngredientViewModel
import com.gemalto.scgrunglometer.viewmodel.RecipeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun LifecycleSafeLaunchedEffect(block: suspend () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                block()
            }
        }
    }
}

@Composable
fun MainApp(
    recipeViewModel: RecipeViewModel,
    ingredientViewModel: IngredientViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destinations.Home,
            modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp)
        ) {
            composable<Destinations.Home> {
                HomeScreen(
                    onRecipesClick = {navController.navigate(Destinations.RecipeList)},
                    onIngredientsClick = {navController.navigate(Destinations.IngredientList)}
                )
            }

            composable<Destinations.IngredientList> {
                val ingredientList = ingredientViewModel.ingredients
                IngredientListScreen(
                    ingredients = ingredientList,
                    onAdd = {
                        ingredientViewModel.addIngredient(it)
                    }
                )
            }

            composable<Destinations.RecipeList> {
                val recipeList = recipeViewModel.recipes
                RecipeListScreen(
                    modifier = modifier,
                    recipes = recipeList,
                    onAdd = {
                        recipeViewModel.addRecipe(it)
                    },
                    onRecipeClicked = {
                        recipeViewModel.setActiveRecipe(it)
                        navController.navigate(Destinations.RecipeDetails)
                    }
                )
            }

            composable<Destinations.RecipeDetails> {
                val recipe = recipeViewModel.recipe
                RecipeScreen(
                    modifier = modifier,
                    recipe = recipe,
                    onNavigateToDetails = {
                        navController.navigate(Destinations.RecipeManagement)
                    }
                )
            }

            composable<Destinations.RecipeManagement> {
                val recipe = recipeViewModel.recipe
                val ingredients = ingredientViewModel.ingredients
                RecipeManagementScreen(
                    recipe = recipe,
                    ingredients = ingredients,
                    onAddIngredient = {
                        recipeViewModel.addIngredientToRecipe(it)
                    },
                    onRemoveIngredient = {
                        recipeViewModel.removeIngredientFromRecipe(it)
                    }
                )
            }
        }
    }
}