package com.gultendogan.foodapp.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Recipe(

    @ColumnInfo(name = "recipeName")
    @SerializedName("recipeName")
    val recipeName:String?,

    @ColumnInfo(name = "recipeIngredients")
    @SerializedName("recipeIngredients")
    val recipeIngredients:String?,

    @ColumnInfo(name = "recipeSteps")
    @SerializedName("recipeSteps")
    val recipeSteps:String?,

    @ColumnInfo(name = "recipeView")
    @SerializedName("recipeView")
    val recipeView:String?) {

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}