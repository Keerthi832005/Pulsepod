package com.example.pulsepod

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pulsepod.model.PodcastModel

class PodcastAdapter(private var podcastList: MutableList<PodcastModel>) :
    RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder>() {

    class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.podcastTitle)
        val description: TextView = itemView.findViewById(R.id.podcastDescription)
        val mood: TextView = itemView.findViewById(R.id.podcastMood)
        val image: ImageView = itemView.findViewById(R.id.podcastImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.podcast_item, parent, false)
        return PodcastViewHolder(view)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val podcast = podcastList[position]
        holder.title.text = podcast.title
        holder.description.text = podcast.description
        holder.mood.text = podcast.mood

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlayerActivity2::class.java).apply {
                putExtra("TITLE", podcast.title)
                putExtra("DESCRIPTION", podcast.description)
                putExtra("IMAGE", podcast.imageResId)
                putExtra("AUDIO_URL", podcast.audioPath)
            }
            holder.itemView.context.startActivity(intent)
        }

        Glide.with(holder.itemView.context)
            .load(podcast.imageResId)
            .placeholder(R.drawable.google)
            .error(R.drawable.google)
            .into(holder.image)
    }

    override fun getItemCount() = podcastList.size

    // ✅ Safely update the list
    fun updateData(newData: List<PodcastModel>) {
        podcastList.clear()
        podcastList.addAll(newData)
        notifyDataSetChanged()
    }
    fun getPodcastAt(position: Int): PodcastModel? {
        return if (position in podcastList.indices) podcastList[position] else null
    }

}
