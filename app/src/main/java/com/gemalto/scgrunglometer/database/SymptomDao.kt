package com.gemalto.scgrunglometer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SymptomDao {

    @Query("SELECT * FROM symptomentity")
    fun getAll(): List<SymptomEntity>

    @Insert
    fun insert(recipe: SymptomEntity): Long
}