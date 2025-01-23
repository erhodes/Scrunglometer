package com.gemalto.scgrunglometer.database

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Recipe

data class RecipeWithIngredients(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    )
    val ingredients: List<IngredientEntity>
)

@Dao
interface RecipeIngredientCrossRefDao {
    @Transaction
    @Query("SELECT * FROM recipeentity")
    fun getRecipesWithIngredients(): List<RecipeWithIngredients>

}