package com.mx.dcc.rickandmortytest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Reference:
 * https://marco-cattaneo.medium.com/android-viewmodel-and-factoryprovider-good-way-to-manage-it-with-dagger-2-d9e20a07084c
 */
@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory
@Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>,
    @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val creator = viewModelMap[modelClass] ?:
        viewModelMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }

}