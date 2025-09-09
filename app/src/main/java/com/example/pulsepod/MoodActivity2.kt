package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MoodActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mood2)

        // Mood buttons
        val moodButtons = listOf(
            R.id.btnCurious,
            R.id.btnProductive,
            R.id.btnRomantic,
            R.id.btnChill,
            R.id.btnSad,
            R.id.btnEnergetic,
            R.id.btnAdventurous
        )

        // Navigate to VibeActivity2 when any mood button is clicked
        for (buttonId in moodButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                val intent = Intent(this, VibeActivity2::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Mixed one button — do NOT go to VibeActivity2
        val mixedButton = findViewById<Button>(R.id.btnMixed)
        mixedButton.setOnClickListener {
            // Do whatever you want for "Mixed one"
            // Example: Show a toast or stay on the same page
        }
    }
}
