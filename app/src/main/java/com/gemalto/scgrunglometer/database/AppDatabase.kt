package com.gemalto.scgrunglometer.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IngredientEntity::class, RecipeEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeDao(): RecipeDao
}