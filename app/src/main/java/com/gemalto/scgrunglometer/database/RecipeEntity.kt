package com.gemalto.scgrunglometer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @ColumnInfo(name="name") val name: String,
    @PrimaryKey(autoGenerate = true) val recipeId: Int = 0
) {
}