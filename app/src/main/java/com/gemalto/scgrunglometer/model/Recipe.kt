package com.gemalto.scgrunglometer.model

class Recipe(
    val name: String,
    val id: Int = -1
) {
    val ingredients = ArrayList<Ingredient>()

    fun copy(): Recipe {
        val result = Recipe(this.name, this.id)
        result.ingredients.addAll(this.ingredients)
        return result
    }
}