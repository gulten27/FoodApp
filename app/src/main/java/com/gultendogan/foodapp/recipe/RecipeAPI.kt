package com.gultendogan.foodapp.recipe

import io.reactivex.Single
import retrofit2.http.GET

interface RecipeAPI {

    //https://github.com/raywenderlich/recipes/blob/master/Recipes.json
    //https://raw.githubusercontent.com/raywenderlich/recipes/master/Recipes.json
    //https://github.com/gulten27/recipes/blob/master/Recipes.json
    //https://raw.githubusercontent.com/gulten27/recipes/master/Recipes.json

    @GET("gulten27/recipes/master/Recipes.json")
    fun getFood() : Single<List<Recipe>>
}