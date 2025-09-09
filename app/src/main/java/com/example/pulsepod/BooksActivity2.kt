package com.example.pulsepod

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsepod.model.BookModel

class BooksActivity2 : AppCompatActivity() {

    private lateinit var bookRecycler: RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books2)

        // Setup RecyclerView
        bookRecycler = findViewById(R.id.bookRecyclerView)
        bookRecycler.layoutManager = LinearLayoutManager(this)

        val books = listOf(
            BookModel("Pride and Prejudice", "Jane Austen", R.drawable.romantic_truth),
            BookModel("The Alchemist", "Paulo Coelho", R.drawable.rounded_image),
            BookModel("1984", "George Orwell", R.drawable.rounded_image),
            BookModel("To Kill a Mockingbird", "Harper Lee", R.drawable.rounded_image)
        )

        bookAdapter = BookAdapter(books)
        bookRecycler.adapter = bookAdapter

        // 🔙 Back Button → close this activity
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // 🏠 Home Button → go to SelectionActivity2
        val homeButton: ImageView = findViewById(R.id.homeButton)
        homeButton.setOnClickListener {
            val intent = Intent(this, SelectionActivity2::class.java)
            startActivity(intent)
            finish()
        }

    }
}
