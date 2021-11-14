package com.rickmorty.cleanarchitecture.presentation.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rickmorty.cleanarchitecture.common.Constants.PARAM_CHARACTER_ID
import com.rickmorty.cleanarchitecture.common.Extensions.toCharacter
import com.rickmorty.cleanarchitecture.common.Resource
import com.rickmorty.cleanarchitecture.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            arguments?.getString(PARAM_CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)

        observeCharacter()

        return binding.root
    }

    private fun observeCharacter() {
        with(viewModel) {
            characterLiveData.observe(viewLifecycleOwner, { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.rickLoadingGif.visibility = VISIBLE
                        binding.characterImageBackgroundView.visibility = GONE
                    }
                    is Resource.Success -> {
                        binding.character = state.data!!.toCharacter()
                        binding.characterImageBackgroundView.visibility = VISIBLE
                        binding.rickLoadingGif.visibility = GONE
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            context,
                            state.message ?: "An error occurred.",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.rickLoadingGif.visibility = GONE
                        binding.characterImageBackgroundView.visibility = GONE
                    }
                }
            })
        }
    }
}
