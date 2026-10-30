package com.pkg.shopnova

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pkg.Adapter.SearchAdapter
import com.pkg.api.JSONParser
import com.pkg.data.GetSearchProductDetails
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.ActivitySearchBinding
import kotlinx.coroutines.launch

private lateinit var searchAdapter: SearchAdapter
private lateinit var binding: ActivitySearchBinding
private var searchList: MutableList<GetSearchProductDetails> = mutableListOf()
lateinit var SearchAdapter: SearchAdapter

lateinit var rvSearch: RecyclerView

private lateinit var rvSearchs: RecyclerView

lateinit var etSearch: EditText
lateinit var imageSearch: ImageView

lateinit var keySearch : String

class SearchActivity : AppCompatActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivitySearchBinding.inflate(layoutInflater)
    setContentView(binding.root)

    etSearch = findViewById<EditText>(R.id.etSearch)



    binding.imageSearch.setOnClickListener {
        try {
            keySearch = etSearch.text.toString()
            if (keySearch.isEmpty()) {

                Toast.makeText(this, "Please enter product name!", Toast.LENGTH_SHORT).show()

            } else {

         // Then call API
                GetAllSearchs(keySearch)
              }

        } catch (e: Exception){
            println("Error imageSearch: ${e.message}")

        }

    }



}

private fun GetAllSearchs(keySearch : String) {

    lifecycleScope.launch {
        try {
            binding.pb.visibility = View.VISIBLE

            val response = AppRetroClient.getApiProduct.searchProduct(keySearch)

            if (response.isSuccessful) {

                val body = response.body()

                if (body != null) {
                    val getAllSearchResponse =
                        JSONParser().parseSearchProducts(Gson().toJson(body))
                    searchList.clear()

                    searchList = getAllSearchResponse.products.toMutableList()


                    if (searchList.size>0) {
                        searchAdapter = SearchAdapter(searchList)
                        binding.rvSearch.layoutManager =
                            LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)

                        binding.rvSearch.adapter = searchAdapter
                    }
                    else
                    {
                      //  Toast.makeText(this, "No product found!", Toast.LENGTH_SHORT).show()

                    }


                    //      searchList.addAll(getAllSearchResponse.products)

                    searchAdapter.notifyDataSetChanged()

                    Log.d("SearchActivity", "Products: ${searchList.size}")
                }

            } else {
                Log.e("SearchActivity", "Error: ${response.code()}")
            }

        } catch (e: Exception) {
            Log.e("SearchActivity", "Exception: ${e.message}", e)

        } finally {
            binding.pb.visibility = View.GONE
        }
    }
}}