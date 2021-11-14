package com.rickmorty.cleanarchitecture.presentation.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.rickmorty.cleanarchitecture.R
import com.rickmorty.cleanarchitecture.common.Constants.PARAM_CHARACTER_ID
import com.rickmorty.cleanarchitecture.common.Extensions.toCharacterList
import com.rickmorty.cleanarchitecture.common.Resource
import com.rickmorty.cleanarchitecture.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(layoutInflater)

        observeCharacters(binding.root)

        return binding.root
    }

    private fun observeCharacters(view: View) {
        with(viewModel) {
            charactersLiveData.observe(viewLifecycleOwner, { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.rickLoadingGif.visibility = VISIBLE
                    }
                    is Resource.Success -> {
                        binding.characterListRv.adapter =
                            CharacterListAdapter(state.data!!.toCharacterList()) {
                                val bundle = bundleOf(PARAM_CHARACTER_ID to it.id)
                                Navigation.findNavController(view)
                                    .navigate(R.id.characterDetailFragment, bundle)
                            }

                        binding.rickLoadingGif.visibility = GONE
                    }
                    is Resource.Error -> {
                        binding.rickLoadingGif.visibility = GONE

                        Toast.makeText(
                            context,
                            state.message ?: "An error occurred.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }
}