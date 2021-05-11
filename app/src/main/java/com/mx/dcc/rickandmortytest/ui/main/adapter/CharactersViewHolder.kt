package com.mx.dcc.rickandmortytest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.dcc.rickandmortytest.R
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.databinding.ItemChracterBinding
import com.mx.dcc.rickandmortytest.utils.loadImageByURL

class CharactersViewHolder(
    private val binding: ItemChracterBinding,
    private val listener: (CharactersModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var character: CharactersModel? = null

    init {
        binding.root.setOnClickListener {
            character?.let { safeCharacter ->
                listener(safeCharacter)
            }
        }
    }

    fun bind(character: CharactersModel?) {
        if (character == null) {
            val resources = itemView.resources
            with(binding) {
                textViewName.text = resources.getString(R.string.loading_msg)
                textViewStatus.text = resources.getString(R.string.item_unknown_status)
                textViewSpecie.text = resources.getString(R.string.item_unknown_specie)
                textViewOrigin.text = resources.getString(R.string.item_unknown_origin)
            }
        } else {
            showCharacterData(character)
        }
    }

    private fun showCharacterData(character: CharactersModel) {
        this.character = character
        val resources = itemView.resources
        with(binding) {
            imageViewCharacter.loadImageByURL(character.image, itemView.context)
            textViewName.text = character.name
            textViewStatus.text = resources.getString(R.string.item_status, character.status)
            textViewSpecie.text = resources.getString(R.string.item_specie, character.species)
            textViewOrigin.text = resources.getString(R.string.item_origin, character.origin)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: (CharactersModel) -> Unit): CharactersViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chracter, parent, false)
            val binding = ItemChracterBinding.bind(view)
            return CharactersViewHolder(binding, listener)
        }
    }

}