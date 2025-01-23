package com.gemalto.scgrunglometer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Recipe
import com.gemalto.scgrunglometer.repository.RecipeRepository

class RecipeViewModel: ViewModel() {

//    var recipe = Recipe("Pho")

    val recipes: SnapshotStateList<Recipe>
        get() = repository.recipes

    private lateinit var repository: RecipeRepository

    var recipe by mutableStateOf(Recipe("Pho"))

    //todo replace this with dependency injection
    fun initialize(repo: RecipeRepository) {
        repository = repo
    }

    fun setActiveRecipe(recipe: Recipe) {
        this.recipe = recipe
    }

    fun addIngredientToRecipe(ingredient: Ingredient) {
        repository.addIngredientToRecipe(recipe, ingredient)

//        recipe.ingredients.add(ingredient)
//
//        recipe = recipe.copy()
    }

    fun addRecipe(name: String) {
        repository.addRecipe(Recipe(name))
    }
}