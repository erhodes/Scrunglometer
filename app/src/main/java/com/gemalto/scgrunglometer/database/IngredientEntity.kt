package com.gemalto.scgrunglometer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gemalto.scgrunglometer.model.Ingredient

@Entity
data class IngredientEntity(
    @PrimaryKey val name: String
) {
    fun toIngredient(): Ingredient {
        return Ingredient(name)
    }
}