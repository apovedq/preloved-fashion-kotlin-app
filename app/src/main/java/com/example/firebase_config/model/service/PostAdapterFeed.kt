import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_config.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebase_config.model.entity.MiniPost
import com.example.firebase_config.model.service.OnFavoritePostSelectedListener
import com.example.firebase_config.views.HomeActivity

class PostAdapterFeed(private val listener: OnFavoritePostSelectedListener, private val posts: List<MiniPost>) : RecyclerView.Adapter<PostAdapterFeed.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.miniPostTitle)
        val imageView: ImageView = itemView.findViewById(R.id.miniPostImage)
        val pointsTextView: TextView = itemView.findViewById(R.id.miniPostPoints)
        val favoriteImageView: ImageView = itemView.findViewById(R.id.favoriteView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.mini_product, parent, false)
        return PostViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.titleTextView.text = post.title
        holder.pointsTextView.text = "  ${post.fashionPoints} FP"

        Glide.with(holder.imageView.context).load(post.image).into(holder.imageView)

        holder.favoriteImageView.setOnClickListener{
            if (post.isAfavorite) {
                post.isAfavorite = false
                holder.favoriteImageView.setImageResource(R.drawable.unfilled_heart)
                listener.onPostDeselected(post)
            }else{
                post.isAfavorite = true
                holder.favoriteImageView.setImageResource(R.drawable.filled_heart)
                listener.onPostSelected(post)
            }
        }

        holder.imageView.setOnClickListener {
            val homeActivity = holder.imageView.context as HomeActivity
            homeActivity.showProductDetail(post.postId)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
