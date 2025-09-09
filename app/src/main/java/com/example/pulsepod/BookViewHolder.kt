package com.example.pulsepod

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bookImage: ImageView = itemView.findViewById(R.id.bookImage)
    val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
    val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)
}
