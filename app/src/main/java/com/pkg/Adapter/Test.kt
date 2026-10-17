/*      btnAddToCart.setOnClickListener {
try{

                    println("btnAddToCart  Clicked!")
                    lifecycleScope.launch {
                          val quantity = tvQuantity.text.toString().toIntOrNull() ?: 1
                            val product = productList[position]

                            val requestAddtoCart = CartRequest(
                                userId = 1,
                                products = listOf(
                                    ProductAddCartRequest(
                                        id = product.id.toInt(),
                                        quantity = quantity
                                    )
                                )
                            )

                                println("CHECK 1: $requestAddtoCart")

                                val addtoCartResponse = AppRetroClient.getApiCart.addToCart(requestAddtoCart).enqueue(object : Callback<CartResponse> {

                                    override fun onResponse(
                                        call: Call<CartResponse>,
                                        response: Response<CartResponse>
                                    ) {
                                        if (response.isSuccessful) {
                                            val cart = response.body()

                                            if (cart != null) {
                                                Toast.makeText(
                                                    this@ProductDetailsActivity,
                                                    "Added to cart successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                // Example: display the cart total
                                                // cart.discountedTotal
                                            }
                                        } else {
                                            Toast.makeText(
                                                this@ProductDetailsActivity,
                                                "Failed to add product: ${response.code()}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<CartResponse>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            this@ProductDetailsActivity,
                                            "Error: ${t.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                    }

}
                        catch (e: Exception) {
                            println("AddTOCart Exception: ${e.message}")
                        }



                }*/
















/*AppRetroClient.getApiCart.placeOrder(request).enqueue(object : Callback<OrderResponse> {

               override fun onResponse(
                   call: Call<OrderResponse>,
                   response: Response<OrderResponse>
               ) {
                   if (response.isSuccessful) {
                       val result = response.body()

                       if (result?.success == true) {
                           Toast.makeText(
                               this@ProductDetailsActivity,
                               result.message,
                               Toast.LENGTH_SHORT
                           ).show()
                       } else {
                           Toast.makeText(
                               this@ProductDetailsActivity,
                               result?.message ?: "Order failed",
                               Toast.LENGTH_SHORT
                           ).show()
                       }
                   } else {
                       Toast.makeText(
                           this@ProductDetailsActivity,
                           "Error: ${response.code()}",
                           Toast.LENGTH_SHORT
                       ).show()
                   }
               }

               override fun onFailure(
                   call: Call<OrderResponse>,
                   t: Throwable
               ) {
                   Toast.makeText(
                       this@ProductDetailsActivity,
                       "Network error: ${t.message}",
                       Toast.LENGTH_SHORT
                   ).show()
               }
           }
           )
           */