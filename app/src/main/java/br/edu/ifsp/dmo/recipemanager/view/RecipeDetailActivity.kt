package br.edu.ifsp.dmo.recipemanager.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.recipemanager.databinding.ActivityRecipeDetailsBinding
import br.edu.ifsp.dmo.recipemanager.model.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding
    private var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipe = intent.getSerializableExtra(EXTRA_RECIPE) as? Recipe


        // Configurar o layout com os dados da receita, se existir
        recipe?.let {
            binding.editTextRecipeName.setText(it.name)
            binding.editTextIngredients.setText(it.ingredients)
            binding.editTextDirections.setText(it.directions)
        }

        // Configurar clique no botão de salvar
        binding.buttonSaveRecipe.setOnClickListener {
            saveRecipe()
        }
    }

    private fun saveRecipe() {
        val name = binding.editTextRecipeName.text.toString()
        val ingredients = binding.editTextIngredients.text.toString()
        val directions = binding.editTextDirections.text.toString()

        if (recipe == null) {
            // Criar uma nova receita
            recipe = Recipe(name, ingredients, directions)
        } else {
            // Editar uma receita existente
            recipe!!.name = name
            recipe!!.ingredients = ingredients
            recipe!!.directions = directions
        }

        // Passar a receita editada de volta para a MainActivity
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_RECIPE, recipe)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }
}