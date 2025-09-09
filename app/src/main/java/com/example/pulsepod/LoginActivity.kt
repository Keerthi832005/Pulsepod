package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var signupText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page) // Replace with your actual layout filename if different

        // Initialize views
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)
        progressBar = findViewById(R.id.progressBar)
        signupText = findViewById(R.id.tvSignin)

        // Handle login click
        loginBtn.setOnClickListener {
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString().trim()

            if (emailInput.isEmpty()) {
                email.error = "Enter your email"
                return@setOnClickListener
            }
            if (passwordInput.isEmpty()) {
                password.error = "Enter your password"
                return@setOnClickListener
            }

            // Simulate login
            progressBar.visibility = View.VISIBLE
            loginBtn.isEnabled = false

            // Simulated delay (e.g., API call)
            loginBtn.postDelayed({
                progressBar.visibility = View.GONE
                loginBtn.isEnabled = true

                // Navigate to main screen or show message
                val intent = Intent(this,LoginSuccessfullActivity::class.java)
                startActivity(intent)
                finish()
                // startActivity(Intent(this, MainActivity::class.java))
                // finish()

            }, 2000)
        }

        // Handle signup click
        signupText.setOnClickListener {
            // Redirect to signup screen
            Toast.makeText(this, "Signup clicked", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
