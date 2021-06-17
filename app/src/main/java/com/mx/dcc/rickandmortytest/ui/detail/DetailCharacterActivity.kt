package com.mx.dcc.rickandmortytest.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mx.dcc.rickandmortytest.R
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.data.detail.CharacterModel
import com.mx.dcc.rickandmortytest.databinding.ActivityDetailCharacterBinding
import com.mx.dcc.rickandmortytest.databinding.ContentDetailCharacterBinding
import com.mx.dcc.rickandmortytest.utils.ApplicationUtils
import com.mx.dcc.rickandmortytest.utils.Constants
import com.mx.dcc.rickandmortytest.utils.DataState
import com.mx.dcc.rickandmortytest.utils.loadImageByURL
import com.mx.dcc.rickandmortytest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailCharacterActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var applicationUtils: ApplicationUtils

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel: DetailCharacterViewModel

    private lateinit var binding: ActivityDetailCharacterBinding
    private lateinit var bindingContent: ContentDetailCharacterBinding

    private lateinit var adapter: CharacterEpisodesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailCharacterViewModel::class.java)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        bindingContent = binding.contentDetailLayout
        setContentView(binding.root)
        val character: CharactersModel? = intent.getParcelableExtra(Constants.NAME_EXTRA_CHARACTER)
        character?.let { safeCharacter ->
            viewModel.setCharacterModel(safeCharacter)
        } ?: finish()

        customToolbar()
        setObservers()

    }

    private fun setObservers() {
        viewModel.characterModel.observe(this) { model -> setDetailsView(model) }
        viewModel.characterEpisodesModel.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    showProgressBar(false)
                    showErrorMessage(false)
                    setEpisodes(dataState.data)
                }
                is DataState.Error -> {
                    showProgressBar(false)
                    showErrorMessage(true)
                }
                is DataState.Loading -> {
                    showProgressBar(true)
                }
            }
        }
    }


    private fun customToolbar() {
        with(binding) {
            collapsingTbLayout.setCollapsedTitleTextColor(resources.getColor(R.color.white))
            collapsingTbLayout.setExpandedTitleColor(resources.getColor(R.color.white))
        }
    }

    private fun setDetailsView(data: CharactersModel) {
        with(binding) {
            imageViewTbCharacter.loadImageByURL(data.image, this@DetailCharacterActivity)
            collapsingTbLayout.title = data.name
        }
        with(bindingContent) {
            textViewStatusDetail.text = getString(R.string.item_status, data.status)
            textViewSpecieDetail.text = getString(R.string.item_specie, data.species)
            textViewOriginDetail.text = getString(R.string.item_origin, data.origin)
            textViewCurrentLocationDetail.text = getString(R.string.item_location, data.location)
        }
    }

    private fun setEpisodes(data: CharacterModel) {
        if (data.episodes.isNotEmpty()) {
            bindingContent.textViewEpisodesHeader.visibility = View.VISIBLE
            adapter = CharacterEpisodesAdapter(data.episodes) {

            }
            with(bindingContent) {
                recyclerViewEpisodes.layoutManager = LinearLayoutManager(this@DetailCharacterActivity)
                recyclerViewEpisodes.adapter = adapter
                recyclerViewEpisodes.visibility = View.VISIBLE
            }
        } else {
            showErrorMessage(true)
        }

    }

    private fun showErrorMessage(show: Boolean) {
        bindingContent.textViewErrorEpisodes.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showProgressBar(show: Boolean) {
        bindingContent.progressBarDetail.visibility = if (show) View.VISIBLE else View.GONE
    }

}