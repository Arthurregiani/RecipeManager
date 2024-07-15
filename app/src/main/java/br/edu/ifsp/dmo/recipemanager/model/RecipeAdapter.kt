package br.edu.ifsp.dmo.recipemanager.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.dmo.recipemanager.R

class RecipeAdapter(context: Context, recipes: List<Recipe>) :
    ArrayAdapter<Recipe>(context, 0, recipes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_recipe, parent, false)
        }

        val recipe = getItem(position)

        val textViewRecipeName = itemView!!.findViewById<TextView>(R.id.textViewRecipeName)
        textViewRecipeName.text = recipe?.name

        return itemView
    }
}