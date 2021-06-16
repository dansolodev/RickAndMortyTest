package com.mx.dcc.rickandmortytest.ui.detail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mx.dcc.rickandmortytest.R
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.databinding.ActivityDetailCharacterBinding
import com.mx.dcc.rickandmortytest.databinding.ContentDetailCharacterBinding
import com.mx.dcc.rickandmortytest.utils.ApplicationUtils
import com.mx.dcc.rickandmortytest.utils.Constants
import com.mx.dcc.rickandmortytest.utils.loadImageByURL
import com.mx.dcc.rickandmortytest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailCharacterActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mApplicationUtils: ApplicationUtils

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel: DetailCharacterViewModel

    private lateinit var binding: ActivityDetailCharacterBinding
    private lateinit var bindingContent: ContentDetailCharacterBinding

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

        setObservers()

    }

    private fun setObservers() {
        viewModel.characterModel.observe(this) { model -> setDetailsView(model) }
    }

    private fun setDetailsView(data: CharactersModel) {
        binding.imageViewTbCharacter.loadImageByURL(data.image, this@DetailCharacterActivity)
        binding.collapsingTbLayout.title = data.name
        with(bindingContent) {
            textViewStatusDetail.text = getString(R.string.item_status, data.status)
            textViewSpecieDetail.text = getString(R.string.item_specie, data.species)
            textViewOriginDetail.text = getString(R.string.item_origin, data.origin)
            textViewCurrentLocationDetail.text = getString(R.string.item_location, data.location)
        }
    }

}