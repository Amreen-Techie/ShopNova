package com.pkg.shopnova

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pkg.api.JSONParser
import com.pkg.data.GetCategoriesData
import com.pkg.data.GetSearchData
import com.pkg.data.GetSearchProductDetails
import com.pkg.recycler_view.CategoryAdapter
import com.pkg.recycler_view.CategoryChildClickListener
import com.pkg.recycler_view.ProductAdapter
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.ActivityCategoryRvBinding
import com.pkg.shopnova.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.collections.forEach

class CategoryRVActivity : AppCompatActivity() , CategoryChildClickListener {

    lateinit var rvCategory: RecyclerView
    lateinit var rvProduct: RecyclerView

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var productAdapter: ProductAdapter

    private var getCategoryList = mutableListOf<GetCategoriesData>()
    private var productCategoryList: MutableList<GetSearchProductDetails> = mutableListOf<GetSearchProductDetails>()

    private lateinit var binding: ActivityCategoryRvBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCategoryRvBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_category_rv)

        rvCategory = findViewById<RecyclerView>(R.id.rv_category)
        rvProduct = findViewById<RecyclerView>(R.id.rv_Product)

        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, windowInsets ->

            val insets = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                view.paddingLeft,
                insets.top,
                view.paddingRight,
                view.paddingBottom
            )

            windowInsets
        }


        getCategoryData()

        getAllProductDetails()
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

                    rvCategory.layoutManager = LinearLayoutManager(this@CategoryRVActivity,LinearLayoutManager.HORIZONTAL,false)
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
    fun getAllProductDetails()
    {
        try
        {
            lifecycleScope.launch {
                binding.pb.visibility = View.VISIBLE

                val response = AppRetroClient.getApiProduct.getAllProduct()

                if (response.isSuccessful) {
                    val jsonString = Gson().toJson(response.body())
                    println("btnGetAllProducts  = ${jsonString}")

                    var jsonParser = JSONParser()
                    var jsonObj = JSONObject(jsonString)
                    println("jsonString  = ${jsonString}")

                    var getSearchResponse = jsonParser.parseSearchProducts(jsonString)

                    println("getAllProductDetails  = ${getSearchResponse}")
                    //productCategoryList = jsonParser.parseSearchProducts(jsonString)
                    productCategoryList = getSearchResponse.products.toMutableList()

                    //productCategoryList = jsonParser.parseSearchProducts(jsonString.toString()) as MutableList<GetSearchData>

                    println("productCategoryList  = ${productCategoryList}")

                    productAdapter = ProductAdapter(productCategoryList as MutableList<GetSearchProductDetails>,this@CategoryRVActivity)

                    rvProduct.layoutManager = LinearLayoutManager(this@CategoryRVActivity,LinearLayoutManager.VERTICAL,false)

                    rvProduct.layoutManager =
                        GridLayoutManager(this@CategoryRVActivity, 2)

                    rvProduct.adapter = productAdapter

                } else {
                    println("Error btnGetAllProducts: ${response.code()}")
                }
               binding.pb.visibility = View.GONE
            }
        }
        catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
    override fun onCategoryClicked(position: Int, name: String) {
        Log.i("CategoryRVActivity", "onNewsClicked called..Item is $name position is $position")
    }
}