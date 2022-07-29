package com.example.marvelworldapp.iu.comics.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelworldapp.R
import com.example.marvelworldapp.databinding.ComicsFragmentBinding
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.iu.common.CharacterRecyclerViewAdapter
import com.example.marvelworldapp.iu.common.ComicRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    // binding

    private var _binding: ComicsFragmentBinding? = null
    private val binding get() = _binding!!

    // view model

    private lateinit var viewModel: ComicsViewModel

    companion object {
        fun newInstance() = ComicsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // conectamos el binding
        _binding = ComicsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model

        viewModel = ViewModelProvider(this).get(ComicsViewModel::class.java)

        initObserver()

        viewModel.getComics()
    }

    private fun initObserver() {
        viewModel.comics.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbListComics.isVisible = it
        }
    }

    private fun initRecyclerView(comics: List<ComicModel>) {
        val manager = GridLayoutManager(context, 3)
        binding.comicsRecyclerView.layoutManager = manager
        binding.comicsRecyclerView.adapter =
            ComicRecyclerViewAdapter(comics) {
                onItemSelected(it)
            }
    }

    /**
     * función para mostrar el fragment de detail
     */
    private fun onItemSelected(comic: ComicModel) {

        // al igual que los personajes, usaremos safe args para los argumentos

        val action = ComicsFragmentDirections.actionComicsFragmentToDetailComicFragment()
        action.idComic = comic.id
        NavHostFragment.findNavController(this).navigate(action)
    }

}