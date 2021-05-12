package com.mx.dcc.rickandmortytest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.mx.dcc.rickandmortytest.R
import com.mx.dcc.rickandmortytest.databinding.CharactersLoadStateFooterViewItemBinding

class CharactersLoadStateViewHolder(
    private val binding: CharactersLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.characters_load_state_footer_view_item, parent, false)
            val binding = CharactersLoadStateFooterViewItemBinding.bind(view)
            return CharactersLoadStateViewHolder(binding, retry)
        }
    }

}