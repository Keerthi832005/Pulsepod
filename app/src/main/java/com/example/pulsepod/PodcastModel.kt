package com.example.pulsepod.model // Or your actual package

data class PodcastModel(
    val title: String,
    val description: String,
    val mood: String,
    val genre: String,
    val imageResId: String,  // For Glide, this would typically be an image URL
    val audioPath: String
)