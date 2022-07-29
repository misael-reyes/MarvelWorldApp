package com.example.marvelworldapp.iu.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworldapp.databinding.CharacterItemBinding
import com.example.marvelworldapp.domain.model.CharacterModel
import com.squareup.picasso.Picasso

/**
 * clase que se encarga de pintar los items de nuestro
 * recycler view
 */
class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // creamos el binding
    private val binding = CharacterItemBinding.bind(view)

    /**
     * función que se llama por cada item de nuestra lista de personajes
     */
    fun render(item: CharacterModel, onClickListener: (CharacterModel) -> Unit) {
        /**
         * nos topamos con un problema, las url que nos proporciona la api tienen la
         * cadena http y para acceder a una imagen necesitamos https, por lo tanto tenemos
         * que concatenarle la letra s
         */
        val cadena_partida = item.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]

        val imageUrl = "$primera_parte/portrait_fantastic.${item.thumbnail_extension}"

        binding.tvNameCharacter.text = item.name
        Picasso.get().load(imageUrl).into(binding.ivCharacter) // con picasso cargamos la imagen

        // le damos evento al recycler
        itemView.setOnClickListener { onClickListener(item) }
    }
}