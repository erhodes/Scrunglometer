package com.gemalto.scgrunglometer.model

class Recipe(
    val name: String,
    val uid: Int = -1
) {
    val ingredients = ArrayList<Ingredient>()

    fun copy(): Recipe {
        val result = Recipe(this.name, this.uid)
        result.ingredients.addAll(this.ingredients)
        return result
    }
}