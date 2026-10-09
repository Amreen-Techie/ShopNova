package com.pkg.shopnova

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pkg.data.LoginRequest
import com.pkg.retrofit.AppRetroClient
import com.pkg.retrofit.MyAppPreference
import com.pkg.shopnova.databinding.LoginBinding
import kotlinx.coroutines.launch

//Login Activity for ecommerce app
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    lateinit  var sharedPref: MyAppPreference
    var token : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        //binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = getString(R.string.app_name)



        sharedPref = MyAppPreference(this@LoginActivity)
        if (isInternetAvailable(this@LoginActivity)) {
            Toast.makeText(this@LoginActivity, "Internet Available", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@LoginActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

        var info = sharedPref.getInfoToken()

        if (!info.isNullOrEmpty()){
            println("ONCREATE My sp Token info is: $info")
            binding.etUsername.setText("emilys")
            binding.etPassword.setText("emilyspass")
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        else
        {
            println("ONCREATE Enter in else")

            binding.etUsername.setText("")
            binding.etPassword.setText("")
        }

        binding.btnLogin.setOnClickListener {
                println("btnLogin  Clicked!")
                lifecycleScope.launch {
                    try {

                        val username_login = binding.etUsername.text.toString().trim()
                    val password_login = binding.etPassword.text.toString().trim()
                    println("username_login: $username_login")
                    println("password_login: $password_login")


                    if (username_login.isEmpty() || password_login.isEmpty()) {
                        Toast.makeText(this@LoginActivity, "Please enter username and password", Toast.LENGTH_SHORT).show()
                    }
                    else if (username_login == "emilys" && password_login == "emilyspass" ) {

                        val login_request = LoginRequest(
                            username_login,
                            password_login
                            ,expiresInMins = 30
                        )
                        println("CHECK 1: $login_request")

                        val login_response = AppRetroClient.getLoginAPI.login(login_request)
                        println("CHECK 2: $login_response")

                        if (login_response.isSuccessful) {

                            val data = login_response.body()

                            if (data != null) {

                                token = data.accessToken
                                val firstName = data.firstName
                                val username = data.username
                                val email = data.email
//Start

                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login successful: $firstName",
                                    Toast.LENGTH_LONG
                                ).show()
//End
                      /*          println("Access Token: $token")
                                println("Username: $username")
                                println("Email: $email")
                                println("firstName: $firstName")
                      */      }

                        } else {
                            println("Error btnLoginClicked: ${login_response.code()}")
                        }

                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()

                       val intent = Intent(this@LoginActivity, MainActivity::class.java)

                        startActivity(intent)

                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }



                    }
                    catch (e: Exception) {
                        println("Try Login Exception: ${e.message}")
                    }

            }
        }


    }
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    override fun onResume() {
        super.onResume()
        sharedPref = MyAppPreference(this@LoginActivity)


        var info = sharedPref.getInfoToken()

        if (!info.isNullOrEmpty()){
            println("ONRESUME My sp Token info is: $info")
            binding.etUsername.setText("emilys")
            binding.etPassword.setText("emilyspass")
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        else
        {
            println("ONRESUME Enter in else")

            binding.etUsername.setText("")
            binding.etPassword.setText("")
        }
    }
    override fun onStop() {
        super.onStop()
        //Fetch data
        var info = token

        // Save Data
        sharedPref.saveInfoToken(info)
    }

    override fun onDestroy() {

        super.onDestroy()
    }
}