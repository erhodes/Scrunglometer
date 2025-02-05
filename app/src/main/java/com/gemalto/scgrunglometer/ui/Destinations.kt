package com.gemalto.scgrunglometer.ui

import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object Home: Destinations()

    @Serializable
    data object IngredientList: Destinations()

    @Serializable
    data object RecipeList: Destinations()

    @Serializable
    data object SymptomsList: Destinations()

    @Serializable
    data object RecipeDetails: Destinations()

    @Serializable
    data object RecipeManagement: Destinations()

    @Serializable
    data class IngredientDetails(val ingredientId: Int): Destinations()

    @Serializable
    data object IngredientManagement: Destinations()
}