package com.creassrpm.deezerapp.models

import java.io.Serializable

//** Artist List **\\

data class ArtistAlbumResponse(
    val id: String,
    val link: String,
    val name: String,
    val nb_album: Int,
    val nb_fan: Int,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val share: String,
    val tracklist: String,
    val type: String
)

//** Artist's Album List**\\

data class AlbumResponse(
    val data: List<AlbumModel>,
    val next: String,
    val total: Int
) : Serializable

data class AlbumModel(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val fans: Int,
    val genre_id: Int,
    val id: String,
    val link: String,
    val md5_image: String,
    val record_type: String,
    val release_date: String,
    val title: String,
    val tracklist: String,
    val type: String
) : Serializable