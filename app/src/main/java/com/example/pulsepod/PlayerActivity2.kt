package com.example.pulsepod

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide

class PlayerActivity2 : AppCompatActivity() {

    private var exoPlayer: ExoPlayer? = null
    private lateinit var playPauseButton: ImageView
    private lateinit var editTextCommand: EditText
    private lateinit var btnSaveCommand: Button
    private var isPrepared = false
    private var isPlaying = false

    // 👇 Keep audioUrl available to the whole class
    private var audioUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player2)

        // 🔙 Back button
        val backButton: ImageView = findViewById(R.id.backbutt)
        backButton.setOnClickListener { finish() }

        // 🎵 Get data from intent
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")
        audioUrl = intent.getStringExtra("AUDIO_URL")  // 👈 store here
        val imageUrl = intent.getStringExtra("IMAGE")

        // 🎨 Views
        val titleText = findViewById<TextView>(R.id.podcastTitle)
        val descriptionText = findViewById<TextView>(R.id.audioTitle)
        val imageView = findViewById<ImageView>(R.id.podcastImage)
        playPauseButton = findViewById(R.id.playPause)
        editTextCommand = findViewById(R.id.editTextCommand)
        btnSaveCommand = findViewById(R.id.btnSaveCommand)

        // 📄 Set UI text
        titleText.text = title ?: "No Title"
        descriptionText.text = description ?: "No Description"

        // 🖼 Load image
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_error)
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.default_image)
        }

        // 🎧 Check audio URL
        if (audioUrl.isNullOrEmpty()) {
            Toast.makeText(this, "No audio URL provided", Toast.LENGTH_SHORT).show()
            playPauseButton.isEnabled = false
            return
        }

        // ▶ Play / ⏸ Pause button listener
        playPauseButton.setOnClickListener {
            if (!isPrepared) {
                initializeExoPlayer(audioUrl!!)
            } else {
                if (isPlaying) pauseAudio() else startAudio()
            }
        }

        // 📌 SharedPreferences for saving command
        val sharedPref = getSharedPreferences("PodcastPrefs", Context.MODE_PRIVATE)

        // Save button click
        btnSaveCommand.setOnClickListener {
            val commandText = editTextCommand.text.toString().trim()
            if (commandText.isNotEmpty()) {
                sharedPref.edit().putString("savedCommand", commandText).apply()
                Toast.makeText(this, "Command has been saved ✅", Toast.LENGTH_SHORT).show()
                editTextCommand.text.clear() // 👈 clear text after saving
            } else {
                Toast.makeText(this, "Please enter a command", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeExoPlayer(url: String) {
        exoPlayer = ExoPlayer.Builder(this).build()
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()

        isPrepared = true
        isPlaying = true
        playPauseButton.setImageResource(R.drawable.pasue) // ⏸ when playing
    }

    private fun startAudio() {
        exoPlayer?.play()
        isPlaying = true
        playPauseButton.setImageResource(R.drawable.pasue) // ⏸
    }

    private fun pauseAudio() {
        exoPlayer?.pause()
        isPlaying = false
        playPauseButton.setImageResource(R.drawable.ic_play) // ▶️
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }
}
