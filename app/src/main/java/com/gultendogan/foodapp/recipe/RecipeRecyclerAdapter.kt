package com.gultendogan.foodapp.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gultendogan.foodapp.R
import com.gultendogan.foodapp.util.imageLoad
import com.gultendogan.foodapp.util.makePlaceholder
import kotlinx.android.synthetic.main.recipe_recycler_row.view.*

class RecipeRecyclerAdapter(val recipeList:ArrayList<Recipe>) : RecyclerView.Adapter<RecipeRecyclerAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recipe_recycler_row,parent,false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.name.text=recipeList.get(position).recipeName

        holder.itemView.setOnClickListener {
            //ROOM
            val action = RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipeList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.imageView.imageLoad(recipeList.get(position).recipeView, makePlaceholder(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun recipeListUpdate(newRecipeList:List<Recipe>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

}