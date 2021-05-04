package com.mx.dcc.rickandmortytest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mx.dcc.rickandmortytest.databinding.ActivityMainBinding
import com.mx.dcc.rickandmortytest.ui.main.CharactersStateEvent
import com.mx.dcc.rickandmortytest.ui.main.CharactersViewModel
import com.mx.dcc.rickandmortytest.utils.DataState
import com.mx.dcc.rickandmortytest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel:CharactersViewModel

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
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Loading -> showProgressBar(true)
                is DataState.Error -> {
                    showProgressBar(false)
                    Toast.makeText(this@MainActivity, dataState.exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
                is DataState.Success -> {
                    showProgressBar(false)
                    Log.d("DATA-API: ", "${dataState.data}")
                }
            }
        }
    }

    private fun showProgressBar(show: Boolean) {
        with(binding.progressBar) {
            if (show) View.VISIBLE else View.GONE
        }
    }

}