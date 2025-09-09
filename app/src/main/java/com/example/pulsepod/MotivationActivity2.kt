package com.example.pulsepod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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

class MotivationActivity2 : AppCompatActivity() {

    private lateinit var podcastRecycler: RecyclerView
    private lateinit var podcastAdapter: PodcastAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation2)

        // ✅ Setup RecyclerView with empty adapter
        podcastRecycler = findViewById(R.id.chillLoveRecyclerView)
        podcastRecycler.layoutManager = LinearLayoutManager(this)
        podcastAdapter = PodcastAdapter(mutableListOf())
        podcastRecycler.adapter = podcastAdapter

        // Fetch podcasts
        fetchPodcastsFromApi()

        // ✅ Handle Play Button
        val playButton = findViewById<ImageView>(R.id.playButton)
        playButton.setOnClickListener {
            if (podcastAdapter.itemCount > 0) {
                val podcast = podcastAdapter.getPodcastAt(0)  // safely fetch first podcast
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

        // ✅ Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_home
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home,
                R.id.nav_discover,
                R.id.nav_library,
                R.id.nav_profile -> {
                    navigateToVibe()
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchPodcastsFromApi() {
        ApiClient.apiService.getMotivationPodcast().enqueue(object : Callback<List<PodcastModel>> {
            override fun onResponse(
                call: Call<List<PodcastModel>>,
                response: Response<List<PodcastModel>>
            ) {
                if (response.isSuccessful) {
                    val motivationPodcasts = response.body()?.filter {
                        it.genre.equals("motivation", ignoreCase = true)
                    } ?: emptyList()

                    podcastAdapter.updateData(motivationPodcasts)
                } else {
                    Toast.makeText(this@MotivationActivity2, "No podcasts found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PodcastModel>>, t: Throwable) {
                Toast.makeText(this@MotivationActivity2, "API failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navigateToVibe() {
        val intent = Intent(this, VibeActivity2::class.java)
        startActivity(intent)
    }
}
