package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.SerieModel

class SerieRecyclerViewAdapter(
    private val seriesList: List<SerieModel>,
    private val onClickListener: (SerieModel) -> Unit
): RecyclerView.Adapter<SerieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SerieViewHolder(layoutInflater.inflate(R.layout.serie_item, parent, false))
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val item = seriesList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = seriesList.size
}