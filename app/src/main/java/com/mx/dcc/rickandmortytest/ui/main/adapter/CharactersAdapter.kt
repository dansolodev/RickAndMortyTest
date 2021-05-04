package com.mx.dcc.rickandmortytest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.dcc.rickandmortytest.R
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.databinding.ItemChracterBinding
import com.mx.dcc.rickandmortytest.utils.Constants
import com.mx.dcc.rickandmortytest.utils.loadImageByURL

class CharactersAdapter(
    private val listener: (CharactersModel) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private var characters: List<CharactersModel> = listOf()
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        mContext = parent.context
        val binding = ItemChracterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        with(holder) {
            with(characters[position]) {
                binding.imageViewCharacter.loadImageByURL(this.image, mContext)
                binding.textViewName.text = this.name
                binding.textViewStatus.text = mContext.getString(R.string.item_status, this.status)
                binding.textViewSpecie.text = mContext.getString(R.string.item_specie, this.species)
                binding.textViewOrigin.text = mContext.getString(R.string.item_origin, this.origin)
                binding.root.setOnClickListener { listener(this) }
            }
        }
    }

    fun setCharacters(characters: List<CharactersModel>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characters.size

    inner class CharactersViewHolder(val binding: ItemChracterBinding)
        : RecyclerView.ViewHolder(binding.root)

}