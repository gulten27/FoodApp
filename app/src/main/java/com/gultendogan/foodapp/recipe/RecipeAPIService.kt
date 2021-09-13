package com.gultendogan.foodapp.recipe

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RecipeAPIService {

    ////https://raw.githubusercontent.com/raywenderlich/recipes/master/Recipes.json
    //https://github.com/gulten27/recipes/blob/master/Recipes.json
    //https://raw.githubusercontent.com/gulten27/recipes/master/Recipes.json

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(RecipeAPI::class.java)

    fun getdata() : Single<List<Recipe>>{
        return api.getFood()
    }
}