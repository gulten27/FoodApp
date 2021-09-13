package com.gultendogan.foodapp.recipe

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.gultendogan.foodapp.recipe.BaseViewModel
import com.gultendogan.foodapp.recipe.Recipe
import com.gultendogan.foodapp.recipe.RecipeDatabase
import kotlinx.coroutines.launch

class RecipeDetailViewModel(application: Application) : BaseViewModel(application) {
    val recipeLiveData = MutableLiveData<Recipe>()

    fun getData(uuid: Int){
        launch {
            val dao = RecipeDatabase(getApplication()).recipeDao()
            val recipe = dao.getRecipe(uuid)
            recipeLiveData.value=recipe
        }
    }
}

