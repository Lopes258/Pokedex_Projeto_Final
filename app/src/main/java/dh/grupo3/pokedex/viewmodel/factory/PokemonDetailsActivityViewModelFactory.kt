package dh.grupo3.pokedex.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dh.grupo3.pokedex.repository.PokemonRepository
import dh.grupo3.pokedex.viewmodel.PokemonDetailsActivityViewModel

class PokemonDetailsActivityViewModelFactory(
    private val repository: PokemonRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonDetailsActivityViewModel(repository) as T
    }

}
