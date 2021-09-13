package com.gultendogan.foodapp.recipe

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class RecipeListViewModel(application: Application) : BaseViewModel(application){
    val recipes = MutableLiveData<List<Recipe>>()
    val recipeLoad = MutableLiveData<Boolean>()
    private lateinit var viewModel: RecipeListViewModel
    private var updateTime = 10*60*1000*1000*1000L

    private val recipeApiService = RecipeAPIService()
    private val disposable = CompositeDisposable()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    fun refreshData() {

        val kaydedilmeZamani = specialSharedPreferences.getTime()

        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime()-kaydedilmeZamani<updateTime){
            getDataFromSqlite()
        }else{
            getDataFromInternet()
        }


    }

    fun refreshFromInternet(){
        getDataFromInternet()
    }


    private fun getDataFromSqlite(){
        recipeLoad.value=true
        launch {
            val recipeLists = RecipeDatabase(getApplication()).recipeDao().getAllRecipe()
            recipesShow(recipeLists)
            Toast.makeText(getApplication(),"Room",Toast.LENGTH_LONG).show()
        }
    }


    private fun getDataFromInternet(){
        recipeLoad.value=true

        disposable.add(
            recipeApiService.getdata()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Recipe>>(){
                    override fun onSuccess(t: List<Recipe>) {
                        sqliteSave(t)
                        Toast.makeText(getApplication(),"Internet",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        recipeLoad.value=false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun recipesShow(recipeLists: List<Recipe>){
        recipes.value=recipeLists
        recipeLoad.value=false
    }

    private fun sqliteSave(recipeList : List<Recipe>){
        launch {
            val dao = RecipeDatabase(getApplication()).recipeDao()
            dao.deleteAllRecipe()
            val uuidList = dao.insertAll(*recipeList.toTypedArray())
            var i = 0
            while (i < recipeList.size){
                recipeList[i].uuid = uuidList[i].toInt()
                i=i+1
            }
            recipesShow(recipeList)
        }

        specialSharedPreferences.timeSave(System.nanoTime())
    }
}
