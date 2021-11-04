package dh.grupo3.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dh.grupo3.pokedex.database.entity.PokemonEntity
import dh.grupo3.pokedex.repository.PokemonRepository

class PokemonFavoriteListActivityViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    fun loadOnlyFavorites(): LiveData<List<PokemonEntity>?> {
        return repository.getFavoritesPokemons()
    }

}
