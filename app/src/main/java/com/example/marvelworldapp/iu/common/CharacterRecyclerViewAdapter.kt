package com.example.marvelworldapp.iu.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.R
import com.example.marvelworldapp.domain.model.CharacterModel

/**
 * clase que se encarga de tomar los personajes obetos y meterlos en el
 * recycler view
 */
class CharacterRecyclerViewAdapter(
    private val characterList: List<CharacterModel>,
    private val onClickListener: (CharacterModel) -> Unit
): RecyclerView.Adapter<CharacterViewHolder>() {

    /**
     * esta función va a devolver un item por cada objeto Character que haya en characterList
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.character_item, parent, false))
    }

    /**
     * esta función va a pasar por cada uno de los item y va a llamar a la función render
     */
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characterList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = characterList.size
}