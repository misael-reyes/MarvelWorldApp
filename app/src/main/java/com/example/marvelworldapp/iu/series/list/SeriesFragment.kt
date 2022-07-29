package com.example.marvelworldapp.iu.series.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelworldapp.R
import com.example.marvelworldapp.databinding.SeriesFragmentBinding
import com.example.marvelworldapp.domain.model.SerieModel
import com.example.marvelworldapp.iu.common.ComicRecyclerViewAdapter
import com.example.marvelworldapp.iu.common.SerieRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment() {

    // binding

    private var _binding: SeriesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SeriesViewModel

    companion object {
        fun newInstance() = SeriesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // conectamos el binding
        _binding = SeriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // conectamos el view model
        viewModel = ViewModelProvider(this).get(SeriesViewModel::class.java)

        initObserver()

        viewModel.getAllSeries()
    }

    private fun initObserver() {
        viewModel.series.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbListSeries.isVisible = it
        }
    }

    private fun initRecyclerView(series: List<SerieModel>) {
        val manager = GridLayoutManager(context, 3)
        binding.seriesRecyclerView.layoutManager = manager
        binding.seriesRecyclerView.adapter =
            SerieRecyclerViewAdapter(series) {
                onItemSelected(it)
            }
    }

    private fun onItemSelected(serie: SerieModel) {
        val action = SeriesFragmentDirections.actionSeriesFragmentToDetailSeriesFragment()
        action.idSeries = serie.id
        NavHostFragment.findNavController(this).navigate(action)
    }

}