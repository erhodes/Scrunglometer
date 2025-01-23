package com.gemalto.scgrunglometer

import com.gemalto.scgrunglometer.model.Recipe

sealed class RecipeState {
    data class ViewingRecipe(val recipe: Recipe): RecipeState()
}