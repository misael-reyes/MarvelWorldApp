package com.example.marvelworldapp.iu.characters.list

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelworldapp.R
import com.example.marvelworldapp.databinding.CharactersFragmentBinding
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.iu.common.CharacterRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: CharactersFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharactersViewModel

    companion object {
        fun newInstance() = CharactersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // conectamos el bindign
        _binding = CharactersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // binding.toolbarCharacters.inflateMenu(R.menu.search_menu)
        binding.svCharacters.setOnQueryTextListener(this)
        // view model
        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)

        initObserver()

        viewModel.getCharacters()
    }

    /**
     * función para inicializar los observadores
     */
    private fun initObserver() {
        viewModel.characters.observe(viewLifecycleOwner) { list_characters ->
            initRecyclerView(list_characters)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbList.isVisible = it
        }
    }

    /**
     * fución para iniciar el recycler view
     */
    private fun initRecyclerView(lista: List<CharacterModel>) {
        val manager = GridLayoutManager(context, 3)
        // val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)   para hacerlo de forma horizontal
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.charactersRecylerView.layoutManager = manager
        binding.charactersRecylerView.adapter =
            CharacterRecyclerViewAdapter(lista) {
                onItemSelected(it)
            }
    }

    /**
     * función que especifica el personaje seleccinoado
     */
    private fun onItemSelected(character: CharacterModel) {
        /**
         * usamos Safe Args para navegar entre destinos, tal como lo recomienda google
         * para mas información consulta:
         *
         * https://developer.android.com/guide/navigation/navigation-navigate?hl=es-419#groovy
         */
        val action = CharactersFragmentDirections.actionCharactersFragmentToDetailCharacterFragment()
        action.idCharacter = character.id
        NavHostFragment.findNavController(this).navigate(action)
        //Log.i("id",character.id.toString())
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewModel.findCharacterByName(query)
        }
        hideKeyboard()
        return true
    }

    private fun hideKeyboard() {

        val view: View? = activity?.currentFocus
        if (view != null) {
            val input: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }



}