package com.gemalto.scgrunglometer.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.gemalto.scgrunglometer.database.AppDatabase
import com.gemalto.scgrunglometer.database.IngredientEntity
import com.gemalto.scgrunglometer.database.RecipeEntity
import com.gemalto.scgrunglometer.database.RecipeIngredientCrossRef
import com.gemalto.scgrunglometer.model.Ingredient
import com.gemalto.scgrunglometer.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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
            Log.d("Eric","RECIPES")
            database.recipeDao().getAll().forEach { entity ->
                Log.d("Eric","$entity")
//                recipes.add(Recipe(entity.name, entity.recipeId))
            }
            Log.d("Eric","INGREDIENTS")
            database.ingredientDao().getAll().forEach {
                Log.d("Eric","$it")
                ingredients.add(it.toIngredient())
            }
            // load in relationships all at once?
            Log.d("Eric","LINKS")
            database.recipeIngredientCrossRefDao().getRecipesWithIngredients().forEach {
                Log.d("Eric","link ${it.recipe} with ${it.ingredients.size}")
                val recipe = Recipe(it.recipe.name, it.recipe.recipeId)
                it.ingredients.forEach { ingredient ->
                    // I think its safe to assume that the ingredients all exist
                    recipe.ingredients.add(getIngredient(ingredient.ingredientId)!!)
                }
                recipes.add(recipe)
            }
        }
    }

    private fun getIngredient(id: Int): Ingredient? {
        return ingredients.find {
            it.id == id
        }
    }

    fun addIngredient(name: String) {
        scope.launch {
            val id = database.ingredientDao().insert(IngredientEntity(name)).toInt()
            ingredients.add(Ingredient(name, id))
        }
    }

    fun addRecipe(name: String) {
        val entity = RecipeEntity(name)

        scope.launch(Dispatchers.IO) {
            // I'm pretty sure this is a safe way to get the ID, but this might be the rowID, and if that's different I'll have to generate IDs myself I think
            val id = database.recipeDao().insert(entity).toInt()
            recipes.add(Recipe(name, id))

            Log.d("Eric","newly created id is $id")
        }
    }

    fun addIngredientToRecipe(recipe: Recipe, ingredient: Ingredient) {
        scope.launch(Dispatchers.IO) {
            database.recipeIngredientCrossRefDao().insertRelationship(RecipeIngredientCrossRef(recipe.id, ingredient.id))
        }
        recipe.ingredients.add(ingredient)
    }

    fun removeIngredientFromRecipe(recipe: Recipe, ingredient: Ingredient) {
        scope.launch(Dispatchers.IO) {
            database.recipeIngredientCrossRefDao().removeRelationship(RecipeIngredientCrossRef(recipe.id, ingredient.id))
        }
        recipe.ingredients.remove(ingredient)
    }
}