package com.gultendogan.foodapp.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RecipeDAO {

    @Insert
    suspend fun insertAll(vararg recipe:Recipe) : List<Long>

    @Query("SELECT*FROM recipe")
    suspend fun getAllRecipe() : List<Recipe>

    @Query("SELECT*FROM recipe WHERE uuid = :recipeId")
    suspend fun getRecipe(recipeId : Int) : Recipe

    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipe()
}