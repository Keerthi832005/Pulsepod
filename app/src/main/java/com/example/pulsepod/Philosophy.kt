package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class Philosophy : AppCompatActivity() {

    private lateinit var podcastRecycler: RecyclerView
    private lateinit var podcastAdapter: PodcastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_philosophy)

        // RecyclerView setup
        podcastRecycler = findViewById(R.id.philosophyRecyclerView)
        podcastRecycler.layoutManager = LinearLayoutManager(this)
        podcastAdapter = PodcastAdapter(mutableListOf())
        podcastRecycler.adapter = podcastAdapter

        // Fetch podcasts from backend
        fetchPodcastsFromApi()

        // Back button
        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            startActivity(Intent(this, VibeActivity2::class.java))
            finish()
        }

        // Books button
        findViewById<Button>(R.id.booksButton).setOnClickListener {
            startActivity(Intent(this, BooksActivity2::class.java))
        }

        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_discover
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, SelectionActivity2::class.java))
                    finish()
                    true
                }
                R.id.nav_discover -> true
                R.id.nav_library -> {
                    Toast.makeText(this, "Library coming soon!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    // Fetch Philosophy podcasts only
    private fun fetchPodcastsFromApi() {
        ApiClient.apiService.getPhilosophyPodcast().enqueue(object : Callback<List<PodcastModel>> {
            override fun onResponse(
                call: Call<List<PodcastModel>>,
                response: Response<List<PodcastModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val philosophyPodcasts = response.body()!!.filter {
                        it.genre.equals("Philosophy", ignoreCase = true)
                    }
                    podcastAdapter.updateData(philosophyPodcasts)
                } else {
                    Toast.makeText(this@Philosophy, "No podcasts found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PodcastModel>>, t: Throwable) {

                Toast.makeText(this@Philosophy, "API failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
