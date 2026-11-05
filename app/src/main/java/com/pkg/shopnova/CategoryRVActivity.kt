package com.pkg.shopnova

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
/**
 * Activity responsible for displaying product categories and their corresponding products
 * in a RecyclerView grid/list layout, with top bar navigation for cart and search actions.
 */

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
    lateinit var imageCart: ImageView
    lateinit var imageSearch: ImageView



   // private val productCategoryList = mutableListOf<GetSearchData>()

    private var allProducts: List<GetSearchData> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display for modern Android window layouts
        enableEdgeToEdge()

        binding = ActivityCategoryRvBinding.inflate(layoutInflater)

        // Inflate layout using View Binding
        setContentView(binding.root)

        // Set click listener for the shopping cart icon to navigate to CartActivity.

        binding.imageCart.setOnClickListener {
            try {

               val intent = Intent(this@CategoryRVActivity, CartActivity::class.java)
                startActivity(intent)

            } catch (e: Exception){
                println("Error imageCart: ${e.message}")

        }}

        //Set click listener for the Search icon to navigate to SearchActivity.

        binding.imageSearch.setOnClickListener {
            try {

                android.util.Log.d("Search_DEBUG", "Search image clicked")

                val intent = Intent(this@CategoryRVActivity, SearchActivity::class.java)
                startActivity(intent)

            } catch (e: Exception){
                println("Error imageSearch: ${e.message}")

            }

        }
        rvCategory = findViewById<RecyclerView>(R.id.rv_category)

        rvProduct = findViewById<RecyclerView>(R.id.rv_Product)

        // Handle window system insets to prevent UI overlap with status/navigation bars

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

        // Fetch initial category data from the API
        getCategoryData()

        // Fetch all product details to populate the initial grid
        getAllProductDetails()


    }

    /**
     * Listener callback handling item clicks on the category horizontal RecyclerView.
     */

    private val categoryClickListener = object : CategoryChildClickListener {

        override fun onCategoryClicked(
            data: ProductCategory,
            position: Int
        ) {
            // Receive clicked category here

            Log.i("CategoryRVActivity", "Item is clicked $position")


            val selectedCategory = getCategoryList.get(position).slug

            // If position is not 0, fetch products specific to the selected category slug;
            // otherwise, load all products when "All" category is selected.

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

            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    /**
     * Fetches the list of product categories from the backend server via Retrofit
     * and sets up the horizontal category RecyclerView.
     */

    fun getCategoryData()
    {
        try {
            lifecycleScope.launch {
                val response = AppRetroClient.getApiCategory.getCategories()

                println(" CATEGORY: $response")

                if (response.isSuccessful) {

                    val jsonString = Gson().toJson(response.body())

                    var jsonParser = JSONParser()

                    // Parse category list from JSON response
                    getCategoryList = jsonParser.parseGetCategories(jsonString.toString()) as MutableList<ProductCategory>

                    // Initialize adapter and assign horizontal layout manager
                    categoryAdapter = CategoryAdapter(
                        getCategoryList,this@CategoryRVActivity,categoryClickListener,binding.toolbar)

                    rvCategory.layoutManager = LinearLayoutManager(this@CategoryRVActivity,LinearLayoutManager.HORIZONTAL,false)
                    rvCategory.adapter = categoryAdapter


                    // Create an "All" option item to reset filters
                    val newItem = ProductCategory(
                        slug = "new-category",
                        name = " All ",
                        url = "https://example.com"
                    )

                    // Insert "All" category at index 0
                    getCategoryList.add(0, newItem)

// Notify RecyclerView
                    categoryAdapter.notifyItemInserted(0)

// Move to the first item
                    rvCategory.scrollToPosition(0)

                } else {

                    println("Error: ${response.code()}")
                }
                /** Log loaded categories for debugging purposes
                getCategoryList.forEach { category ->


                    println("name = ${category.name}")
                    println(category.slug)
                    println(category.url)
                }
*/

            }

        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    /**
     * Fetches products filtered by a specific category slug and updates the product grid.
     */

    private fun  getProductByCategory(categoryName : String)
    {
            try
            {
                lifecycleScope.launch {

                    // Show loading progress bar
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

                        // Setup product adapter with a 2-column grid layout manager
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
    /**
     * Fetches all available products from the server and displays them in a 2-column grid.
     */
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