package com.gemalto.scgrunglometer.repository

import androidx.compose.runtime.mutableStateListOf
import com.gemalto.scgrunglometer.database.AppDatabase
import com.gemalto.scgrunglometer.database.IngredientEntity
import com.gemalto.scgrunglometer.database.RecipeEntity
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeRepository(
    private val database: AppDatabase,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {

    val recipes = mutableStateListOf<Recipe>()
    val ingredients = mutableStateListOf<Ingredient>()

    init {
        scope.launch(Dispatchers.IO) {
            //load in existing recipes from database
            database.recipeDao().getAll().forEach { entity ->
                recipes.add(Recipe(entity.name, entity.uid))
            }
            database.ingredientDao().getAll().forEach {
                ingredients.add(it.toIngredient())
            }
        }
    }

    fun addIngredient(ingredient: Ingredient) {
        scope.launch {
            database.ingredientDao().insertAll(IngredientEntity(ingredient.name))
        }
        ingredients.add(ingredient)
    }

    fun addRecipe(recipe: Recipe) {
        val entity = RecipeEntity(recipe.name)

        scope.launch(Dispatchers.IO) {
            database.recipeDao().insertAll(entity)
        }
        recipes.add(recipe)
    }

    fun addIngredientToRecipe(recipe: Recipe, ingredient: Ingredient) {
        scope.launch(Dispatchers.IO) {

        }
    }
}