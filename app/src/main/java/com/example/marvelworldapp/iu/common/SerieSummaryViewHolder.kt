package com.example.marvelworldapp.iu.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.databinding.SerieItemSummaryBinding
import com.example.marvelworldapp.domain.model.SerieModel
import com.squareup.picasso.Picasso

/**
 * clase que se encarga de pintar los items de nuestras series en
 * el recycler view
 */
class SerieSummaryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    //creamos el binding
    private val binding = SerieItemSummaryBinding.bind(view)

    /**
     * función que se llama por cada item de nuestra lista de series
     */
    fun render(serie: SerieModel) {
        val cadena_partida = serie.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]

        val imageUrl = "$primera_parte/portrait_fantastic.${serie.thumbnail_extension}"

        binding.tvSerieSummary.text = serie.title
        Picasso.get().load(imageUrl).into(binding.ivSerieSummary)
    }
}