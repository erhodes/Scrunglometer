package com.gemalto.scgrunglometer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingrediententity")
    fun getAll(): List<IngredientEntity>

    @Insert
    fun insert(ingredient: IngredientEntity): Long
}