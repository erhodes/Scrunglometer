package com.gemalto.scgrunglometer.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IngredientEntity::class, RecipeEntity::class, SymptomEntity::class, RecipeIngredientCrossRef::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeDao(): RecipeDao
    abstract fun symptomDao(): SymptomDao
    abstract fun recipeIngredientCrossRefDao(): RecipeIngredientCrossRefDao
}