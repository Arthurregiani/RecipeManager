package br.edu.ifsp.dmo.recipemanager.model
import java.io.Serializable

data class Recipe(
    var name: String = "",
    var ingredients: String = "",
    var directions: String = ""
) : Serializable

