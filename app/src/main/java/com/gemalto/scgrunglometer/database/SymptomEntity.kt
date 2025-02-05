package com.gemalto.scgrunglometer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gemalto.scgrunglometer.model.Symptom

@Entity
data class SymptomEntity(
    @ColumnInfo(name="name") val name: String,
    @PrimaryKey(autoGenerate = true) val symptomId: Int = 0
) {
    fun toSymptom(): Symptom {
        return Symptom(this.name, this.symptomId)
    }
}