package com.mx.dcc.rickandmortytest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mx.dcc.rickandmortytest.R
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.databinding.ActivityMainBinding
import com.mx.dcc.rickandmortytest.ui.main.CharactersStateEvent
import com.mx.dcc.rickandmortytest.ui.main.CharactersViewModel
import com.mx.dcc.rickandmortytest.ui.main.adapter.CharactersAdapter
import com.mx.dcc.rickandmortytest.utils.DataState
import com.mx.dcc.rickandmortytest.utils.shoMessageToast
import com.mx.dcc.rickandmortytest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel: CharactersViewModel
    private lateinit var adapter: CharactersAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharactersViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        viewModel.setStateEvent(CharactersStateEvent.GetCharactersEvent)
    }

    private fun setObservers() {
        viewModel.dataState.observe(this) { event ->
            event.getContentIfNotHandled()?.let { dataState ->
                when (dataState) {
                    is DataState.Loading -> showProgressBar(true)
                    is DataState.Error -> {
                        showProgressBar(false)
                        this@MainActivity.shoMessageToast(
                            dataState.exception.localizedMessage
                                ?: getString(R.string.general_error_message),
                            Toast.LENGTH_LONG
                        )
                    }
                    is DataState.Success -> {
                        showProgressBar(false)
                        setCharacters(dataState.data)
                    }
                }
            }
        }

    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setCharacters(items: List<CharactersModel>) {
        adapter = CharactersAdapter {}
        adapter.setCharacters(items)
        binding.recyclerViewCharacters.visibility = View.VISIBLE
        binding.recyclerViewCharacters.adapter = adapter
    }

}