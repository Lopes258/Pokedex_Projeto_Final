package dh.grupo3.pokedex.model.pokemonInformation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonType (
    val slot : Int,
    val type : TypeInformations
    ) : Parcelable