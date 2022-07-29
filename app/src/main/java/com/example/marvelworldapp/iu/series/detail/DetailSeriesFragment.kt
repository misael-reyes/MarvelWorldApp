package com.example.marvelworldapp.iu.series.detail

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
import com.example.marvelworldapp.databinding.DetailSeriesFragmentBinding
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.model.SerieModel
import com.example.marvelworldapp.domain.useCase.GetSeriesUseCase
import com.example.marvelworldapp.iu.common.CharacterSummaryRecyclerViewAdapter
import com.example.marvelworldapp.iu.common.ComicSummaryRecyclerViewAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailSeriesFragment : Fragment() {

    // configaración del binding

    private var _binding: DetailSeriesFragmentBinding? = null
    private val binding get() = _binding!!

    // inyectamos el caso de uso
    @Inject lateinit var useCase: GetSeriesUseCase

    // atributo para recuperar los parametros
    private val args: DetailSeriesFragmentArgs by navArgs()

    companion object {
        fun newInstance() = DetailSeriesFragment()
    }

    // inicialización del view model

    private val viewModel: DetailSeriesViewModel by viewModels {
        DetailSeriesViewModelFactory(args.idSeries, useCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailSeriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()

        viewModel.getSeries()
    }

    private fun initObserver() {
        viewModel.series.observe(viewLifecycleOwner) {
            loadDataSeries(it) // cargamos los datos de la serie
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbDetailSeries.isVisible = it
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataSeries(series: SerieModel) {
        val cadena_partida = series.thumbnail.split(":")
        val primera_parte = cadena_partida[0] + "s:" + cadena_partida[1]
        val imageUrl = "$primera_parte/landscape_incredible.${series.thumbnail_extension}"

        Picasso.get().load(imageUrl).into(binding.imageSeriesDetail)
        binding.nameSeriesDetail.text = series.title
        binding.descriptionSeries.text = series.description
        binding.titleCharactersSeries.text = "Characters"
        binding.titleComicsSeries.text = "Comics"

        // inicializamos los recycler view internos

        initRecyclerViewCharacters(series.characters)
        initRecyclerViewComics(series.comics)
    }

    private fun initRecyclerViewCharacters(characters: List<CharacterModel>) {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCharactersOfSeries.layoutManager = manager
        binding.rvCharactersOfSeries.adapter = CharacterSummaryRecyclerViewAdapter(characters)
    }

    private fun initRecyclerViewComics(comics: List<ComicModel>) {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvComicsOfSeries.layoutManager = manager
        binding.rvComicsOfSeries.adapter = ComicSummaryRecyclerViewAdapter(comics)
    }
}