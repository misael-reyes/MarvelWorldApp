package com.example.marvelworldapp.iu.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.databinding.ComicItemSummaryBinding
import com.example.marvelworldapp.domain.model.ComicModel
import com.squareup.picasso.Picasso

class ComicSummaryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ComicItemSummaryBinding.bind(view)

    fun render(item: ComicModel) {
        val cadena_partida = item.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]

        val imageUrl = "$primera_parte/portrait_fantastic.${item.thumbnail_extension}"

        binding.tvNameComic.text = item.title
        Picasso.get().load(imageUrl).into(binding.ivComic)
    }
}