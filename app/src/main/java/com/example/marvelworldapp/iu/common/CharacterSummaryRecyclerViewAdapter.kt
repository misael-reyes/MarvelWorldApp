package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.CharacterModel

class CharacterSummaryRecyclerViewAdapter(
    private val lista: List<CharacterModel>
): RecyclerView.Adapter<CharacterSummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSummaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterSummaryViewHolder(layoutInflater.inflate(R.layout.character_item_summary, parent, false))
    }

    override fun onBindViewHolder(holderSummary: CharacterSummaryViewHolder, position: Int) {
        val item = lista[position]
        holderSummary.render(item)
    }

    override fun getItemCount(): Int = lista.size
}