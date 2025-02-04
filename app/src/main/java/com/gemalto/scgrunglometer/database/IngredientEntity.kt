package com.gemalto.scgrunglometer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gemalto.scgrunglometer.model.Ingredient

@Entity
data class IngredientEntity(
    @ColumnInfo(name="name") val name: String,
    @PrimaryKey(autoGenerate = true) val ingredientId: Int = 0
) {
    fun toIngredient(): Ingredient {
        return Ingredient(name, ingredientId)
    }
}