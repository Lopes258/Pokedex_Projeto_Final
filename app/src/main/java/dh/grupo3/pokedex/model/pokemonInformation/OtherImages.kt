package dh.grupo3.pokedex.model.pokemonInformation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtherImages(
    val front_default : String
) : Parcelable
