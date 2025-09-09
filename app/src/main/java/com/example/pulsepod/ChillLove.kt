package com.example.pulsepod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsepod.model.PodcastModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChillLove : AppCompatActivity() {

    private lateinit var podcastRecycler: RecyclerView
    private lateinit var podcastAdapter: PodcastAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chill_love2)

        // ✅ RecyclerView
        podcastRecycler = findViewById(R.id.chillLoveRecyclerView)
        podcastRecycler.layoutManager = LinearLayoutManager(this)
        podcastAdapter = PodcastAdapter(mutableListOf())
        podcastRecycler.adapter = podcastAdapter

        // ✅ Fetch podcasts
        fetchPodcastsFromApi()

        // ✅ Play button
        val playButton = findViewById<ImageView>(R.id.playButton)
        playButton.setOnClickListener {
            if (podcastAdapter.itemCount > 0) {
                val podcast = podcastAdapter.getPodcastAt(0)
                if (podcast != null) {
                    val intent = Intent(this, PlayerActivity2::class.java).apply {
                        putExtra("TITLE", podcast.title)
                        putExtra("DESCRIPTION", podcast.description)
                        putExtra("IMAGE", podcast.imageResId)
                        putExtra("AUDIO_URL", podcast.audioPath)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No podcast loaded yet", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No podcast loaded yet", Toast.LENGTH_SHORT).show()
            }
        }
        // ✅ Back button → navigate to VibeActivity2
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, VibeActivity2::class.java)
            startActivity(intent)
            finish()
        }
        // ✅ Books button → open BooksActivity2
        val booksButton = findViewById<Button>(R.id.booksButton)
        booksButton.setOnClickListener {
            val intent = Intent(this, BooksActivity2::class.java)
            startActivity(intent)
        }
        booksButton.setOnClickListener {
            Toast.makeText(this, "Books clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BooksActivity2::class.java)
            startActivity(intent)
        }


        // ✅ Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_home
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, SelectionActivity2::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_discover -> {
                    navigateToVibe()
                    true
                }
                R.id.nav_library -> {
                    Toast.makeText(this, "Library coming soon!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    // ✅ API Fetch
    private fun fetchPodcastsFromApi() {
        ApiClient.apiService.getPodcasts().enqueue(object : Callback<List<PodcastModel>> {
            override fun onResponse(
                call: Call<List<PodcastModel>>,
                response: Response<List<PodcastModel>>
            ) {
                if (response.isSuccessful) {
                    val lovePodcasts = response.body()?.filter {
                        it.genre.equals("love", ignoreCase = true)
                    } ?: emptyList()

                    podcastAdapter.updateData(lovePodcasts)
                } else {
                    Toast.makeText(this@ChillLove, "No podcasts found", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<List<PodcastModel>>, t: Throwable) {
                Toast.makeText(this@ChillLove, "API failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    // ✅ Navigate to Vibe Screen
    private fun navigateToVibe() {
        val intent = Intent(this, VibeActivity2::class.java)
        startActivity(intent)
    }

}
