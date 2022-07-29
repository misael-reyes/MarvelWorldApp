package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.ComicModel

class ComicRecyclerViewAdapter(
    private val comicsList: List<ComicModel>,
    private val onclickListener: (ComicModel) -> Unit
): RecyclerView.Adapter<ComicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicViewHolder(layoutInflater.inflate(R.layout.comic_item, parent, false))
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val item = comicsList[position]
        holder.render(item, onclickListener)
    }

    override fun getItemCount(): Int  = comicsList.size
}