package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.ComicModel

class ComicSummaryRecyclerViewAdapter(
    private val comicsList: List<ComicModel>
): RecyclerView.Adapter<ComicSummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicSummaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicSummaryViewHolder(layoutInflater.inflate(R.layout.comic_item_summary, parent, false))
    }

    override fun onBindViewHolder(holderSummary: ComicSummaryViewHolder, position: Int) {
        val item = comicsList[position]
        holderSummary.render(item)
    }

    override fun getItemCount(): Int = comicsList.size
}