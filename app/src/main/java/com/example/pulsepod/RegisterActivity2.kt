package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsepod.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity2 : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etCreatePassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvSignup: TextView
    private lateinit var tvSignin: TextView  // ← Add this line


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        // Bind views
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail) // You originally called this etMobile, but XML says etEmail
        etCreatePassword = findViewById(R.id.etCreatePassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tvSignup = findViewById(R.id.tvSignup)
        tvSignin = findViewById(R.id.tvSignin)  // ← Link your TextView from XML

        // Navigate to Login page when "Sign In" is clicked
        tvSignin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity2, LoginActivity::class.java))
            finish()
        }

        // Sign-up click listener
        tvSignup.setOnClickListener {
            val name = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etCreatePassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // API call
            ApiClient.apiService.registerUser(name, email, password, confirmPassword)
                .enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful) {
                            val res = response.body()
                            Toast.makeText(
                                this@RegisterActivity2,
                                res?.message ?: "Registration successful",
                                Toast.LENGTH_LONG

                            ).show()
                            // Redirect to LoginActivity
                            val intent = Intent(this@RegisterActivity2, LoginActivity::class.java)
                            startActivity(intent)
                            finish() // Optional: closes RegisterActivity so it can't be returned to
                        } else {
                            Toast.makeText(
                                this@RegisterActivity2,
                                "Registration failed: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(
                            this@RegisterActivity2,
                            "Network Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}

