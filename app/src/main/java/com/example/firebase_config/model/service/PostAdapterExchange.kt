package com.example.firebase_config.model.service

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebase_config.R
import com.example.firebase_config.model.entity.MiniPost

class PostAdapterExchange(private val listener: OnPostSelectedListener) : RecyclerView.Adapter<PostAdapterExchange.PostViewHolder>() {

    var posts = mutableListOf<MiniPost>()
    private var postsSelected = mutableListOf<MiniPost>()
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.miniPostImage)
        val pointsTextView: TextView = itemView.findViewById(R.id.miniPostPoints)
        val cardView: CardView = itemView.findViewById(R.id.cardIV)
        val circle: ImageView = itemView.findViewById(R.id.circle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.selected_mini_product, parent, false)
        return PostViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.pointsTextView.text = "  ${post.fashionPoints} FP"

        Glide.with(holder.imageView.context).load(post.image).into(holder.imageView)

        holder.imageView.setOnClickListener {
            if (post.isSelected) {
                post.isSelected = false
                postsSelected.remove(post)

                holder.circle.setImageResource(R.drawable.gray_check)
                holder.cardView.setCardBackgroundColor(Color.parseColor("#AFAFAF"))
            } else {
                post.isSelected = true
                postsSelected.add(post)
                holder.circle.setImageResource(R.drawable.purple_check)
                holder.cardView.setCardBackgroundColor(Color.parseColor("#5F4BC4"))
            }

            listener.onPostSelected(postsSelected)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun addPosts(posts: List<MiniPost>){
        this.posts = posts.toMutableList()
    }
}