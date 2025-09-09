import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsepod.model.PodcastModel
import com.example.pulsepod.R

class ChillLoveAdapter(private val podcastList: List<PodcastModel>) :
    RecyclerView.Adapter<ChillLoveAdapter.PodcastViewHolder>() {

    inner class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.podcastImage)
        val title = itemView.findViewById<TextView>(R.id.podcastTitle)
        val desc = itemView.findViewById<TextView>(R.id.podcastDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chilllove, parent, false)
        return PodcastViewHolder(view)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val podcast = podcastList[position]
        holder.title.text = podcast.title
        holder.desc.text = podcast.description
        // You can load images using Glide or Picasso:
        // Glide.with(holder.image.context).load(podcast.imageResId).into(holder.image)
    }

    override fun getItemCount(): Int = podcastList.size
}
