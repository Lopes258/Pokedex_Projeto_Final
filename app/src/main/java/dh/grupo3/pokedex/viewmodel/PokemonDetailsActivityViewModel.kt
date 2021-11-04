package dh.grupo3.pokedex.viewmodel

import androidx.lifecycle.*
import dh.grupo3.pokedex.database.entity.PokemonEntity
import dh.grupo3.pokedex.repository.PokemonRepository
import dh.grupo3.pokedex.repository.Resource
import kotlinx.coroutines.launch

class PokemonDetailsActivityViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    private val pokemonLiveData = MediatorLiveData<Resource<PokemonEntity?>>()

    fun getPokemonById(pokemonId: Int): LiveData<Resource<PokemonEntity?>> {

        pokemonLiveData.addSource(repository.getPokemonByIdOnDatabase(pokemonId)) {
            if (it != null) {
                pokemonLiveData.value = Resource(data = it)
            } else {
                viewModelScope.launch {
                    pokemonLiveData.value = repository.getPokemonByIdOnApi(pokemonId).value
                }
            }
        }
        return pokemonLiveData
    }

    fun changeFavorite(pokemonReferente: PokemonEntity) {
        viewModelScope.launch {
            repository.changeFavorite(pokemonReferente)
        }
    }

}
