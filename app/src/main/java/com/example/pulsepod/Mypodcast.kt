package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Mypodcast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypodcast)

        // Back button click → go to SelectionActivity2
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, SelectionActivity2::class.java)
            startActivity(intent)
            finish() // close this activity
        }
    }

    // Handle Android system back button as well
    override fun onBackPressed() {
        val intent = Intent(this, SelectionActivity2::class.java)
        startActivity(intent)
        finish()
    }
}
