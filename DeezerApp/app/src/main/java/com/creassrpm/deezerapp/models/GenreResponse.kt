package com.creassrpm.deezerapp.models

import java.io.Serializable


//*** Deezer Genre List***\\

data class DeezerGenreResponse(
    val data: List<GenreModel>
) : Serializable

data class GenreModel(
    val id: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val type: String
) : Serializable