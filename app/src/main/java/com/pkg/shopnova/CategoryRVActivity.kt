package com.pkg.shopnova

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.pkg.data.ProductCategory
import com.pkg.data.GetSearchData
import com.pkg.data.GetSearchProductDetails
import com.pkg.recycler_view.CategoryAdapter
import com.pkg.recycler_view.CategoryChildClickListener
import com.pkg.recycler_view.ProductAdapter
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.ActivityCategoryRvBinding
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.collections.forEach

class CategoryRVActivity : AppCompatActivity()  {

    lateinit var rvCategory: RecyclerView
    lateinit var rvProduct: RecyclerView

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var productAdapter: ProductAdapter
    //lateinit var categoryClickListener : CategoryChildClickListener

    private var getCategoryList = mutableListOf<ProductCategory>()
    private var productCategoryList: MutableList<GetSearchProductDetails> = mutableListOf<GetSearchProductDetails>()

    private var getProductListByCategory = mutableListOf<GetSearchProductDetails>()
    private lateinit var binding: ActivityCategoryRvBinding

    private val categoryClickListener = object : CategoryChildClickListener {

        override fun onCategoryClicked(
            data: ProductCategory,
            position: Int
        ) {
            // Receive clicked category here

            Log.i("CategoryRVActivity", "Item is clicked $position")
//            Toast.makeText(
//                this@CategoryRVActivity,
//                getCategoryList.get(position).toString(),
//                Toast.LENGTH_SHORT
//            ).show()

            val selectedCategory = getCategoryList.get(position).slug
            // Load products for selected category
            if (position!=0)
            {

                println("Adapter Enters in if getProductByCategory: $selectedCategory")

                getProductByCategory(selectedCategory)

            }
            else{

                println("Adapter Enters in else getCategoryData: $selectedCategory")
                getAllProductDetails()

                // getCategoryData()

            }
        }
    }

   // private val productCategoryList = mutableListOf<GetSearchData>()

    private var allProducts: List<GetSearchData> = emptyList()
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

// Get the category details
        getCategoryData()

// Get the product details
        getAllProductDetails()

   //     getProductByCategories("smartphones")



    }

    fun getCategoryData()
    {
        try {
            lifecycleScope.launch {
                val response = AppRetroClient.getApiCategory.getCategories()

                println(" CATEGORY: $response")

                if (response.isSuccessful) {

                    val jsonString = Gson().toJson(response.body())

                    var jsonParser = JSONParser()
                    getCategoryList = jsonParser.parseGetCategories(jsonString.toString()) as MutableList<ProductCategory>




                    categoryAdapter = CategoryAdapter(
                        getCategoryList,this@CategoryRVActivity,categoryClickListener)

                    rvCategory.layoutManager = LinearLayoutManager(this@CategoryRVActivity,LinearLayoutManager.HORIZONTAL,false)
                    rvCategory.adapter = categoryAdapter


                    // Create a new item
                    val newItem = ProductCategory(
                        slug = "new-category",
                        name = " All ",
                        url = "https://example.com"
                    )

// Add at position 0
                    getCategoryList.add(0, newItem)

// Notify RecyclerView
                    categoryAdapter.notifyItemInserted(0)

// Move to the first item
                    rvCategory.scrollToPosition(0)

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

    private fun  getProductByCategory(categoryName : String)
    {
            try
            {
                lifecycleScope.launch {
                    binding.pb.visibility = View.VISIBLE
                    val response = AppRetroClient.getApiProduct.getProductByCategories(categoryName)

                    if (response.isSuccessful) {
                        val jsonString = Gson().toJson(response.body())
                        println("GetProductByCategories  = ${jsonString}")

                        var jsonParser = JSONParser()
                        var jsonObj = JSONObject(jsonString)
                        println("jsonString  = ${jsonString}")

                        var getProductByCategoryResponse = jsonParser.parseSearchProducts(jsonString)

                        println("GetProductByCategories  = ${getProductByCategoryResponse}")

                        getProductListByCategory = getProductByCategoryResponse.products.toMutableList()

                        println("getProductListByCategory  = ${getProductListByCategory}")

                        productAdapter = ProductAdapter(getProductListByCategory as MutableList<GetSearchProductDetails>,categoryClickListener)

                        rvProduct.layoutManager = LinearLayoutManager(this@CategoryRVActivity,LinearLayoutManager.VERTICAL,false)

                        rvProduct.layoutManager =
                            GridLayoutManager(this@CategoryRVActivity, 2)

                        rvProduct.adapter = productAdapter


                    } else {
                        println("Error GetProductByCategories: ${response.code()}")
                    }
                    binding.pb.visibility = View.GONE
                }
            }
            catch (e: Exception) {
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

                    productAdapter = ProductAdapter(productCategoryList as MutableList<GetSearchProductDetails>,categoryClickListener)

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
   /*  fun onCategoryClicked(position: Int, name: String) {
        Log.i("CategoryRVActivity", "onNewsClicked called..Item is $name position is $position")
    }*/

}