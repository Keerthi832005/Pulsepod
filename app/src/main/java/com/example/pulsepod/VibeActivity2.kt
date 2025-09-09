package com.example.pulsepod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class VibeActivity2 : AppCompatActivity() {
    lateinit var topLogo: ImageView
    lateinit var loveButton: Button
    lateinit var motivationButton: Button
    lateinit var philoButton: Button
    lateinit var btnFiction: Button
    lateinit var btnSelfHelp: Button
    lateinit var btnMusic: Button
    lateinit var btnPopCulture: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vibe2)

        // Back button
        topLogo = findViewById(R.id.topLogo)
        topLogo.setOnClickListener {
            finish()
        }

        // ❤️ Love button
        loveButton = findViewById(R.id.btnLove)
        motivationButton=findViewById(R.id.btnMotivation)
        philoButton=findViewById(R.id.btnPhilo)
        btnFiction=findViewById(R.id.btnFiction)
        btnSelfHelp=findViewById(R.id.btnSelfHelp)
        btnMusic=findViewById(R.id.btnMusic)
        btnPopCulture=findViewById(R.id.btnPopCulture)

        loveButton.setOnClickListener {
            val intent = Intent(this, ChillLove::class.java)
            startActivity(intent)
        }
        motivationButton.setOnClickListener {
            val intent = Intent(this, MotivationActivity2::class.java)
            startActivity(intent)
        }
        philoButton.setOnClickListener {
            val intent = Intent(this, Philosophy::class.java)
            startActivity(intent)
        }
        btnFiction.setOnClickListener {
            val intent = Intent(this, Fiction::class.java)
            startActivity(intent)
        }
        btnPopCulture.setOnClickListener {
            val intent = Intent(this, Popculture::class.java)
            startActivity(intent)
        }

    }
}
