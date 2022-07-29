package com.example.marvelworldapp.iu.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.databinding.SerieItemBinding
import com.example.marvelworldapp.domain.model.SerieModel
import com.squareup.picasso.Picasso

class SerieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = SerieItemBinding.bind(view)

    fun render(item: SerieModel, onClickListener: (SerieModel) -> Unit) {
        val cadena_partida = item.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]

        val imageUrl = "$primera_parte/portrait_fantastic.${item.thumbnail_extension}"

        binding.tvSerieList.text = item.title
        Picasso.get().load(imageUrl).into(binding.ivSerieList)
        itemView.setOnClickListener { onClickListener(item) }
    }
}