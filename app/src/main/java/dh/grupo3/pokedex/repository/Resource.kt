package dh.grupo3.pokedex.repository

data class Resource<T>(
    val data: T?,
    val error: String? = null
)