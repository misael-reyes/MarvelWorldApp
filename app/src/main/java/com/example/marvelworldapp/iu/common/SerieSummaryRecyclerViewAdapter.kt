package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.SerieModel

class SerieSummaryRecyclerViewAdapter(
    private val comicsList: List<SerieModel>
): RecyclerView.Adapter<SerieSummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieSummaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SerieSummaryViewHolder(layoutInflater.inflate(R.layout.serie_item_summary, parent, false))
    }

    override fun onBindViewHolder(holderSummary: SerieSummaryViewHolder, position: Int) {
        val item = comicsList[position]
        holderSummary.render(item)
    }

    override fun getItemCount(): Int = comicsList.size
}