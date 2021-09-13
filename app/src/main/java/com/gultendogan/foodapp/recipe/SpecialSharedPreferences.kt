package com.gultendogan.foodapp.recipe

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

//ROOM

class SpecialSharedPreferences {

    companion object{
        private val TIME = "time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : SpecialSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context: Context) : SpecialSharedPreferences= instance ?: synchronized(lock){
            instance ?: specialSharedPreferencesMake(context).also {
                instance = it
            }
        }

        private fun specialSharedPreferencesMake(context: Context) : SpecialSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SpecialSharedPreferences()
        }
    }

    fun timeSave(time: Long){
        sharedPreferences?.edit(commit = true){
            putLong(TIME,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(TIME,0)

}