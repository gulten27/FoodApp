package com.gultendogan.foodapp.recipe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Recipe::class),version = 1)
abstract class RecipeDatabase : RoomDatabase(){

    abstract fun recipeDao() : RecipeDAO

    companion object {

        @Volatile private var instance : RecipeDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseCreate(context).also {
                instance=it
            }
        }

        private fun databaseCreate(context: android.content.Context) = Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "recipedatabase"
        ).build()

    }


}