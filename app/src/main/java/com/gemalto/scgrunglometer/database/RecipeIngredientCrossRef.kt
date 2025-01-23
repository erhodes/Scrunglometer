package com.gemalto.scgrunglometer.database

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "ingredientId"])
data class RecipeIngredientCrossRef(
    val recpipeId: Int,
    val ingredientId: String
)