package com.mx.dcc.rickandmortytest.ui.main.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mx.dcc.rickandmortytest.data.CharactersModel

class CharactersAdapter(
    private val listener: (CharactersModel) -> Unit
) : PagingDataAdapter<CharactersModel, CharactersViewHolder>(CHARACTER_COMPARATOR) {


    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<CharactersModel>() {
            override fun areItemsTheSame(
                oldItem: CharactersModel,
                newItem: CharactersModel
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: CharactersModel,
                newItem: CharactersModel
            ): Boolean = oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val charaterItem = getItem(position)
        if (charaterItem != null) {
            holder.bind(charaterItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.create(parent,listener)
    }

}