package com.example.marvelworldapp.iu.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.databinding.CharacterItemSummaryBinding
import com.example.marvelworldapp.domain.model.CharacterModel
import com.squareup.picasso.Picasso

class CharacterSummaryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CharacterItemSummaryBinding.bind(view)

    fun render(item: CharacterModel) {
        val cadena_partida = item.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]

        val imageUrl = "$primera_parte/portrait_fantastic.${item.thumbnail_extension}"

        binding.tvCharacterSummary.text = item.name
        Picasso.get().load(imageUrl).into(binding.ivCharacterSummary)
    }
}