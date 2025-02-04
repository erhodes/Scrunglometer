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

        //todo this is a hack to force the ui to update. I think the only alternative is an event flow from the Repo
        setActiveRecipe(recipe.copy())
    }

    fun removeIngredientFromRecipe(ingredient: Ingredient) {
        repository.removeIngredientFromRecipe(recipe, ingredient)
        //todo this is a hack to force the ui to update
        setActiveRecipe(recipe.copy())
    }

    fun addRecipe(name: String) {
        repository.addRecipe(name)
    }
}