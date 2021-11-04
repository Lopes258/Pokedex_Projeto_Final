package dh.grupo3.pokedex.viewmodel

import androidx.lifecycle.*
import dh.grupo3.pokedex.database.entity.PokemonEntity
import dh.grupo3.pokedex.repository.PokemonRepository
import dh.grupo3.pokedex.repository.Resource
import kotlinx.coroutines.launch

class PokemonListActivityViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    fun findAll(): LiveData<Resource<List<PokemonEntity>?>> {
        return repository.findAll()
    }

    fun loadMorePokemons(pokemonId: Int): LiveData<Resource<PokemonEntity?>> {
        val pokemonLiveData = MutableLiveData<Resource<PokemonEntity?>>()
        viewModelScope.launch {

            val pokemonResource = repository.getPokemonByIdOnApi(pokemonId).value
            pokemonResource?.let { resource ->
                pokemonLiveData.postValue(resource)
                resource.data.let { pokemon ->
                    repository.insertPokemonOnDatabase(pokemon)
                }
            }
        }
        return pokemonLiveData
    }

    fun getOnPokemonByNameOrId(nameOrId: String?): MutableLiveData<Resource<List<PokemonEntity>?>> {
        val liveDataSearch = MutableLiveData<Resource<List<PokemonEntity>?>>()
        viewModelScope.launch {
            liveDataSearch.postValue(repository.getPokemonByNameOrId(nameOrId).value)
        }
        return liveDataSearch
    }
}