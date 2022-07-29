package com.example.marvelworldapp.iu.characters.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelworldapp.databinding.DetailCharacterFragmentBinding
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.model.SerieModel
import com.example.marvelworldapp.domain.useCase.GetCharacterUseCase
import com.example.marvelworldapp.iu.common.ComicSummaryRecyclerViewAdapter
import com.example.marvelworldapp.iu.common.SerieSummaryRecyclerViewAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCharacterFragment: Fragment() {

    // configuración del binding
    private var _binding: DetailCharacterFragmentBinding? = null
    private val binding get() = _binding!!

    // inyectamos un objeto GetCharacterUseCase para pasarselo a nuestor viewmodel factory
    @Inject lateinit var casoUso: GetCharacterUseCase

    // esto es para recuperar los parametros que nos pasaron por Safe Args
    private val args: DetailCharacterFragmentArgs by navArgs()

    companion object {
        fun newInstance() = DetailCharacterFragment()
    }

    /**
     * inicializamos nuestro view model inyectandole como dependencia
     * el id del personaje seleccionado y los casos de uso a utilizar
     */
    private val viewModel: DetailCharacterViewModel by viewModels {
        DetailCharacterViewModelFactory(args.idCharacter, casoUso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return inflater.inflate(R.layout.detail_character_fragment, container, false)
        _binding = DetailCharacterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // tambien de esta forma se puede iniciarlilzar el viewmodel
        //viewModel = ViewModelProvider(this, DetailCharacterViewModelFactory(parametros)).get(DetailCharacterViewModel::class.java)

        initObserver()

        viewModel.getCharacter()
    }

    /**
     * iniciamos los observadores
     */
    private fun initObserver() {
        viewModel.character.observe(viewLifecycleOwner) {
            loadDataCharacter(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbDetail.isVisible = it
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataCharacter(character: CharacterModel) {
        val cadena_partida = character.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]
        val imageUrl = "$primera_parte/landscape_incredible.${character.thumbnail_extension}"

        // pintamos los datos del personaje en el fragment
        Picasso.get().load(imageUrl).into(binding.imageCharacterDetail)
        binding.nameCharacterDetail.text = character.name
        binding.descriptionCharacter.text = character.description
        binding.titleComics.text = "Comics"
        binding.titleSeries.text = "Series"

        initRecyclerViewComic(character.comics)
        initRecyclerViewSerie(character.series)
    }

    private fun initRecyclerViewComic(comics: List<ComicModel>) {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.comicsDetailRecyclerView.layoutManager = manager
        binding.comicsDetailRecyclerView.adapter = ComicSummaryRecyclerViewAdapter(comics)
    }

    private fun initRecyclerViewSerie(series: List<SerieModel>) {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.seriesDetailRecyclerView.layoutManager = manager
        binding.seriesDetailRecyclerView.adapter = SerieSummaryRecyclerViewAdapter(series)
    }

    /**
     * asi inicializa el view model artis dev
     * private val viewModel: DetailCharacterViewModel by viewModels()
     *
     * asi inicializa el view model con factory dev experto
     * private val viewModel: DetailCharacterViewModel by viewModels { DetailCharacterViewModelFactory(parametro) }
     */
}