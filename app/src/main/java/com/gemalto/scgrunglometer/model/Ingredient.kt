package com.gemalto.scgrunglometer.model

class Ingredient(
    val name: String,
    val id: Int = -1
) {
    val associatedSymptoms = ArrayList<Symptom>()

    fun addSymptom(symptom: Symptom) {
        associatedSymptoms.add(symptom)
    }
}