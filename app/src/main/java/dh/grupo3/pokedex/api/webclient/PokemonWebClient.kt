package dh.grupo3.pokedex.api.webclient

import dh.grupo3.pokedex.api.RetroftInstance
import dh.grupo3.pokedex.api.service.PokeApiService

class PokemonWebClient(
    private val service: PokeApiService = RetroftInstance().pokeApiService
) {
    suspend fun findPokemonById(id: Int) = service.getPokemonById(id)
    suspend fun findPokemonByIdOrName(nameOrId: String) = service.getPokemonByNameOrId(nameOrId)
}