package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.CreatorModel

class CreatorSummaryRecyclerViewAdapter(
    private val lista: List<CreatorModel>
): RecyclerView.Adapter<CreatorSummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorSummaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CreatorSummaryViewHolder(layoutInflater.inflate(R.layout.creator_item_summary, parent, false))
    }

    override fun onBindViewHolder(holder: CreatorSummaryViewHolder, position: Int) {
        val item = lista[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = lista.size
}