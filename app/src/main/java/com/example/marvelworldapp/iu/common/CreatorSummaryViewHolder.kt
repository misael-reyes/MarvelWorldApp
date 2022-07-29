package com.example.marvelworldapp.iu.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.databinding.CreatorItemSummaryBinding
import com.example.marvelworldapp.domain.model.CreatorModel
import com.squareup.picasso.Picasso

class CreatorSummaryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CreatorItemSummaryBinding.bind(view)

    fun render(item: CreatorModel) {
        val cadena_partida = item.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]

        val imageUrl = "$primera_parte/portrait_fantastic.${item.thumbnail_extension}"

        binding.tvCreatorSummary.text = item.fullName
        Picasso.get().load(imageUrl).into(binding.ivCreatorSummary)
    }
}