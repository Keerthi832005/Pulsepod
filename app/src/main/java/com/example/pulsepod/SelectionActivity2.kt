package com.example.pulsepod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class SelectionActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selection2)

        // ✅ Button 1 → Go to Podcast
        val goToPodcastButton = findViewById<Button>(R.id.btnGoToPodcast)
        goToPodcastButton.setOnClickListener {
            val intent = Intent(this, MoodActivity2::class.java)
            startActivity(intent)
        }

        // ✅ Button 2 → Listen to My Podcast
        val listenToMyPodcastBtn = findViewById<Button>(R.id.btnListenToMyPodcast)
        listenToMyPodcastBtn.setOnClickListener {
            val intent = Intent(this, Mypodcast::class.java)
            startActivity(intent)
        }
    }
}
