package com.mx.dcc.rickandmortytest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.mx.dcc.rickandmortytest.databinding.ActivityMainBinding
import com.mx.dcc.rickandmortytest.ui.main.CharactersViewModel
import com.mx.dcc.rickandmortytest.ui.main.adapter.CharactersAdapter
import com.mx.dcc.rickandmortytest.ui.main.adapter.CharactersLoadStateAdapter
import com.mx.dcc.rickandmortytest.utils.showMessageToast
import com.mx.dcc.rickandmortytest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel: CharactersViewModel
    private lateinit var adapter: CharactersAdapter
    private lateinit var binding: ActivityMainBinding

    private var charactersJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharactersViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        getCharacters()
        initCharacters()
        binding.buttonMainRetry.setOnClickListener { adapter.retry() }
    }

    private fun initCharacters() {
        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerViewCharacters.scrollToPosition(0) }
        }
    }

    private fun getCharacters() {
        // Make sure we cancel the previous job before creating a new one
        charactersJob?.cancel()
        charactersJob = lifecycleScope.launch {
            viewModel.getCharacters().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        adapter = CharactersAdapter { character ->
            showMessageToast(character.name)
        }
        binding.recyclerViewCharacters.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharactersLoadStateAdapter { adapter.retry() },
            footer = CharactersLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // show empty list
            val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmptyList)
            handlerUIBy(loadState)
        }
    }

    private fun handlerUIBy(loadState: CombinedLoadStates) {
        // Only show the list if refresh succeeds.
        binding.recyclerViewCharacters.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh
        binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        // Show the retry state if initial load or refresh fails.
        binding.textViewMainError.isVisible = loadState.source.refresh is LoadState.Error
        binding.buttonMainRetry.isVisible = loadState.source.refresh is LoadState.Error
        showError(loadState)
    }

    private fun showError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorState?.let {
            showMessageToast("\uD83D\uDE28 Wooops ${it.error}", Toast.LENGTH_LONG)
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.recyclerViewCharacters.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.recyclerViewCharacters.visibility = View.VISIBLE
        }
    }

}