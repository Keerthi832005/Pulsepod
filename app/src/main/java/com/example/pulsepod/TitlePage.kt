package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TitlePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_page)

        val logoImage = findViewById<ImageView>(R.id.logoImage)

        // Fade-in animation
        logoImage.alpha = 0f
        logoImage.animate().alpha(1f).setDuration(1000).start()

        // Delay and navigate to RegisterActivity2
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RegisterActivity2::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
