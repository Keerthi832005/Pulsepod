package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginSuccessfullActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_successfull)

        val successTextView: TextView = findViewById(R.id.successText)
        successTextView.text = "Welcome! Login was successful."

        // Navigate to MoodActivity2 after 3 seconds (3000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SelectionActivity2::class.java)
            startActivity(intent)
            finish() // optional: closes this activity
        }, 1000)
    }
}
