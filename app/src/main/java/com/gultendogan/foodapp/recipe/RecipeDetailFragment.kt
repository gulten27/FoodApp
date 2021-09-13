package com.gultendogan.foodapp.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gultendogan.foodapp.R
import com.gultendogan.foodapp.util.imageLoad
import com.gultendogan.foodapp.util.makePlaceholder
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : Fragment() {

    private lateinit var viewModel: RecipeDetailViewModel
    private var recipeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            recipeId = RecipeDetailFragmentArgs.fromBundle(it).recipeId

        }

        viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel::class.java)
        viewModel.getData(recipeId)

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.recipeLiveData.observe(viewLifecycleOwner, Observer { recipe ->
            recipe?.let {
                recipeName.text = it.recipeName
                recipeIngredients.text = it.recipeIngredients
                recipeSteps.text=it.recipeSteps
                //ROOM
                context?.let {
                    recipeImage.imageLoad(recipe.recipeView, makePlaceholder(it))
                }

            }
        })
    }

}