package com.gultendogan.foodapp.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gultendogan.foodapp.R
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment : Fragment() {

    private lateinit var viewModel: RecipeListViewModel
    private val recyclerRecipeAdapter = RecipeRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
        viewModel.refreshData()

        recipeListRecycler.layoutManager = LinearLayoutManager(context)
        recipeListRecycler.adapter = recyclerRecipeAdapter

        swipeRefreshLayout.setOnRefreshListener {
            recipeListLoading.visibility=View.VISIBLE
            recipeListRecycler.visibility=View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let {
                recipeListRecycler.visibility=View.VISIBLE
                recyclerRecipeAdapter.recipeListUpdate(recipes)
            }
        })

        viewModel.recipeLoad.observe(viewLifecycleOwner, Observer { load ->
            load?.let {
                if(it){
                    recipeListRecycler.visibility=View.GONE
                    recipeListLoading.visibility=View.VISIBLE
                }else{
                    recipeListLoading.visibility=View.GONE
                }
            }
        })
    }
   
}