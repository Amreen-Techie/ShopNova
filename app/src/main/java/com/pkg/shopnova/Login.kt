package com.pkg.shopnova

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pkg.data.LoginRequest
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.LoginBinding
import kotlinx.coroutines.launch
//Login Activity for ecommerce app
class Login : AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            try {
                println("btnLogin  Clicked!")

                  /*  val login_request = LoginRequest(
                        username = "emilys",
                        password = "emilyspass",
                        expiresInMins = 30
                    )

                    val login_response = AppRetroClient.getLoginAPI.login(login_request)

                    if (login_response.isSuccessful) {

                        val data = login_response.body()

                        if (data != null) {

                            val token = data.accessToken
                            val firstName = data.firstName
                            val username = data.username
                            val email = data.email


                            Toast.makeText(
                                this@MainActivity,
                                "Login successful: $firstName",
                                Toast.LENGTH_LONG
                            ).show()

                            println("Access Token: $token")
                            println("Username: $username")
                            println("Email: $email")

                            binding.tvName.setText("Username :$username")
                            binding.tvUrl.setText("Email Id :$email")

                        }

                    } else {
                        println("Error: ${login_response.code()}")
                    }*/


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
    }
}