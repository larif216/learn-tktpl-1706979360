package com.example.helloworldapp.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.helloworldapp.R
import com.example.helloworldapp.models.Hero

class HeroRecyclerViewAdapter(
        private val heroesList: List<Hero>,
        private val listener: OnHeroClickListener
        )
    : RecyclerView.Adapter<HeroRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_hero, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroesList[position]

        Glide.with(holder.itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350,350))
                .into(holder.photo)
        holder.name.text = hero.name
    }

    override fun getItemCount(): Int = heroesList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val photo: ImageView = view.findViewById(R.id.photo)
        val name: TextView = view.findViewById(R.id.name)
        init {
            view.setOnClickListener(View.OnClickListener {
                listener.onHeroClick(heroesList[position])
            })
        }
    }

    interface OnHeroClickListener {
        fun onHeroClick(hero: Hero)
    }
}