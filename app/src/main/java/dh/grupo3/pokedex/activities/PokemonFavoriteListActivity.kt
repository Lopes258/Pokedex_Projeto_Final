package dh.grupo3.pokedex.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dh.grupo3.pokedex.R

import dh.grupo3.pokedex.adapter.RecyclerAdapterMain
import dh.grupo3.pokedex.database.AppDatabase
import dh.grupo3.pokedex.database.entity.PokemonEntity
import dh.grupo3.pokedex.repository.PokemonRepository
import dh.grupo3.pokedex.util.POKEMON_CHAVE
import dh.grupo3.pokedex.viewmodel.PokemonFavoriteListActivityViewModel
import dh.grupo3.pokedex.viewmodel.factory.PokemonFavoriteListActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_pokemon_favorite_list.*


class PokemonFavoriteListActivity : AppCompatActivity() {

    private val adapter by lazy {
        RecyclerAdapterMain(context = this)
    }
    private val layoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val viewModel by lazy {
        val repository = PokemonRepository(AppDatabase.getInstance(this).pokemonDAO)
        val factory = PokemonFavoriteListActivityViewModelFactory(repository)
        ViewModelProvider(this, factory)
            .get(PokemonFavoriteListActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_favorite_list)
        startConfig()
        loadFavorites()

        materialAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    private fun startConfig() {
        recycleView_main.layoutManager = layoutManager
        recycleView_main.adapter = adapter
        adapter.onItemClicked = ::openPokemonDetails
    }

    private fun openPokemonDetails(pokemonId: PokemonEntity) {
        val intent = Intent(this, PokemonDetailsActivity::class.java)
        intent.putExtra(POKEMON_CHAVE, pokemonId.id)
        startActivity(intent)
    }

    private fun loadFavorites() {
        viewModel.loadOnlyFavorites().observe(this, {listPokemons ->
            listPokemons?.let{
                adapter.update(it)
            }
        })
    }
}