package com.rickmorty.cleanarchitecture.presentation.character_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rickmorty.cleanarchitecture.databinding.CharacterListRowDesignBinding
import com.rickmorty.cleanarchitecture.domain.model.Character
import com.rickmorty.cleanarchitecture.presentation.character_list.CharacterListAdapter.ViewHolder

class CharacterListAdapter(
    private val characters: List<Character>, val listener: (Character) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterListRowDesignBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(characters[position])

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: CharacterListRowDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                this.character = character
                executePendingBindings()
                root.setOnClickListener { listener(character) }
            }
        }
    }
}