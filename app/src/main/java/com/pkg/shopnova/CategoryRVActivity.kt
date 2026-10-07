package com.pkg.shopnova

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pkg.api.JSONParser
import com.pkg.data.GetCategoriesData
import com.pkg.recycler_view.CategoryAdapter
import com.pkg.recycler_view.CategoryChildClickListener
import com.pkg.retrofit.AppRetroClient
import kotlinx.coroutines.launch
import kotlin.collections.forEach

class CategoryRVActivity : AppCompatActivity() , CategoryChildClickListener {
    lateinit var fragmentContainer : FrameLayout

    lateinit var rvCategory: RecyclerView
    lateinit var categoryAdapter: CategoryAdapter
    private var getCategoryList = mutableListOf<GetCategoriesData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_category_rv)

        rvCategory = findViewById<RecyclerView>(R.id.rv_category)


        getCategoryData()


    }
    fun getCategoryData()
    {
        try {
            lifecycleScope.launch {
                val response = AppRetroClient.getApiCategory.getCategories()


                if (response.isSuccessful) {
                    val jsonString = Gson().toJson(response.body())

                    var jsonParser = JSONParser()

                    getCategoryList = jsonParser.parseGetCategories(jsonString.toString()) as MutableList<GetCategoriesData>
                    categoryAdapter = CategoryAdapter(getCategoryList as MutableList<GetCategoriesData>,this@CategoryRVActivity)

                    rvCategory.layoutManager = LinearLayoutManager(this@CategoryRVActivity)
                    rvCategory.adapter = categoryAdapter


                } else {

                    println("Error: ${response.code()}")
                }
                getCategoryList.forEach { category ->
                    println("name = ${category.name}")
                    println(category.slug)
                    println(category.url)
                }


            }

        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    override fun onCategoryClicked(position: Int, name: String) {
        Log.i("CategoryRVActivity", "onNewsClicked called..Item is $name position is $position")
    }
}