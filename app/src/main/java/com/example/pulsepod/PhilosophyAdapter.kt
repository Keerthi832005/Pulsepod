package com.example.pulsepod

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsepod.model.PodcastModel

class PhilosophyAdapter(private var podcastList: List<PodcastModel>) :
    RecyclerView.Adapter<PhilosophyAdapter.PodcastViewHolder>() {

    inner class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.podcastImage)
        val title: TextView = itemView.findViewById(R.id.podcastTitle)
        val desc: TextView = itemView.findViewById(R.id.podcastDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chilllove, parent, false)
        return PodcastViewHolder(view)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val podcast = podcastList[position]
        holder.title.text = podcast.title
        holder.desc.text = podcast.description

        // ✅ on click → open PlayerActivity2
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, PlayerActivity2::class.java).apply {
                putExtra("TITLE", podcast.title)
                putExtra("DESCRIPTION", podcast.description)
                putExtra("IMAGE", podcast.imageResId)
                putExtra("AUDIO_URL", podcast.audioPath)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = podcastList.size

    fun updateData(newList: List<PodcastModel>) {
        podcastList = newList
        notifyDataSetChanged()
    }
}
