package com.gemalto.scgrunglometer.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.repository.RecipeRepository

class IngredientViewModel: ViewModel() {

    private lateinit var repository: RecipeRepository

    val ingredients: SnapshotStateList<Ingredient>
        get() = repository.ingredients

    //todo replace this with dependency injection
    fun initialize(repo: RecipeRepository) {
        repository = repo
    }

    fun addIngredient(name: String) {
        repository.addIngredient(name)
    }
}