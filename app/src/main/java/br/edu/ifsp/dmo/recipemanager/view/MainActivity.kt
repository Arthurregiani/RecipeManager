package br.edu.ifsp.dmo.recipemanager.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.recipemanager.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.recipemanager.model.Recipe
import br.edu.ifsp.dmo.recipemanager.model.RecipeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipes: MutableList<Recipe> = mutableListOf()

    private val startRecipeDetailActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val editedRecipe = data?.getSerializableExtra(RecipeDetailActivity.EXTRA_RECIPE) as? Recipe
                editedRecipe?.let {
                    if (it !in recipes) {
                        recipes.add(it)
                    } else {
                        val index = recipes.indexOfFirst { recipe -> recipe == it }
                        if (index != -1) {
                            recipes[index] = it
                        }
                    }
                    recipeAdapter.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simulação de dados - você pode carregar dados reais aqui
        recipes.add(Recipe("Bolo de Chocolate", "Farinha, açúcar, chocolate", "Passo 1, Passo 2"))
        recipes.add(Recipe("Salada Caesar", "Alface, croutons, parmesão", "Passo 1, Passo 2"))

        recipeAdapter = RecipeAdapter(this, recipes)
        binding.listViewRecipes.adapter = recipeAdapter

        // Configurar clique em um item da lista para abrir a tela de detalhes/edição
        binding.listViewRecipes.setOnItemClickListener { parent, view, position, id ->
            val selectedRecipe = recipes[position]
            openRecipeDetail(selectedRecipe)
        }

        // Configurar clique no botão de adicionar receita para abrir a tela de detalhes/edição
        binding.buttonAddRecipe.setOnClickListener {
            openRecipeDetail(null) // Passando null indica que é uma nova receita
        }
    }

    private fun openRecipeDetail(recipe: Recipe?) {
        val intent = Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe)
        }
        startRecipeDetailActivity.launch(intent)
    }


    companion object {
        private const val REQUEST_CODE_EDIT_RECIPE = 1
    }
}
