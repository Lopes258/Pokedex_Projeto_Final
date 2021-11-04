package dh.grupo3.pokedex.model.pokemonInformation

import android.os.Parcelable
import dh.grupo3.pokedex.model.pokemonInformation.OtherSprit
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSprite(
    val other : OtherSprit
) : Parcelable
