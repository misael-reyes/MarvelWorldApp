package com.example.marvelworldapp.iu.comics.detail

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
import com.example.marvelworldapp.databinding.DetailComicFragmentBinding
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.model.CreatorModel
import com.example.marvelworldapp.domain.useCase.GetComicUseCase
import com.example.marvelworldapp.iu.common.CharacterSummaryRecyclerViewAdapter
import com.example.marvelworldapp.iu.common.CreatorSummaryRecyclerViewAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailComicFragment : Fragment() {

    // configracion del binding
    private var _binding: DetailComicFragmentBinding? = null
    private val binding get() = _binding!!

    /**
     * vamos a injectar un caso de uso apra pasarselo al factory de nuestro
     * view model
     */
    @Inject lateinit var useCase: GetComicUseCase

    // atributo para recuperar los datos que nos pasen del fragment principal
    private val args: DetailComicFragmentArgs by navArgs()

    companion object {
        fun newInstance() = DetailComicFragment()
    }

    /**
     * inicializamos nuestro view model inyectandole como dependencia
     * el id del personaje seleccionado y los casos de uso a utilizar
     */
    private val viewModel: DetailComicViewModel by viewModels {
        DetailComicViewModelFactory(args.idComic, useCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailComicFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // tambien de esta forma se puede iniciarlilzar el viewmodel
        //viewModel = ViewModelProvider(this, DetailCharacterViewModelFactory(parametros)).get(DetailCharacterViewModel::class.java)

        initObserver()

        viewModel.getComic()
    }

    private fun initObserver() {
        viewModel.comic.observe(viewLifecycleOwner) {
            loadDataComic(it) // cargamos los datos del comic
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbDetailComics.isVisible = it
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataComic(comic: ComicModel) {
        val cadena_partida = comic.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]
        val imageUrl = "$primera_parte/landscape_incredible.${comic.thumbnail_extension}"

        Picasso.get().load(imageUrl).into(binding.imageComicDetail)
        binding.nameComicDetail.text = comic.title
        binding.descriptionComic.text = comic.description
        binding.titleCharactersComics.text = "Characters"
        binding.titleCreatorsComics.text = "Creators"

        // inicializamos los recycler view internos

        initRecyclerViewCharacters(comic.characters)
        initRecyclerViewCreators(comic.creators)
    }

    private fun initRecyclerViewCharacters(characters: List<CharacterModel>) {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCharactersOfComic.layoutManager = manager
        binding.rvCharactersOfComic.adapter = CharacterSummaryRecyclerViewAdapter(characters)
    }

    private fun initRecyclerViewCreators(creators: List<CreatorModel>) {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCreatorsOfComic .layoutManager = manager
        binding.rvCreatorsOfComic.adapter = CreatorSummaryRecyclerViewAdapter(creators)
    }
}