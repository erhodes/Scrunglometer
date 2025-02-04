package com.gemalto.scgrunglometer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipeentity")
    fun getAll(): List<RecipeEntity>

    @Insert
    fun insert(recipe: RecipeEntity): Long
}