package com.example.firebase_config.model.service

import android.annotation.SuppressLint
import android.graphics.Color
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

class PostAdapterExchange(private val posts: List<MiniPost>) : RecyclerView.Adapter<PostAdapterExchange.PostViewHolder>() {

    var selectedPosts = mutableListOf<MiniPost>()
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
        holder.pointsTextView.text = post.fashionPoints

        Glide.with(holder.imageView.context).load(post.image).into(holder.imageView)

        holder.imageView.setOnClickListener {
            val selected = selectedPosts.contains(post)
            if (selected) {
                selectedPosts.remove(post)
                holder.circle.setImageResource(R.drawable.gray_check)
                holder.cardView.setCardBackgroundColor(Color.parseColor("#AFAFAF"))
            } else {
                selectedPosts.add(post)
                holder.circle.setImageResource(R.drawable.purple_check)
                holder.cardView.setCardBackgroundColor(Color.parseColor("#5F4BC4"))
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun getSumOfFP(): Int {
        return selectedPosts.sumOf { it.fashionPoints.toInt() }
    }
}