package com.mx.dcc.rickandmortytest.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.dcc.rickandmortytest.databinding.ItemEpisodeBinding

class CharacterEpisodesAdapter(
    private val items: List<String>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<CharacterEpisodesAdapter.EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val binding = ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.textViewEpisode.text = this
                binding.root.setOnClickListener { listener(this) }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class EpisodesViewHolder(
        val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root)

}