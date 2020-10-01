package com.creassrpm.deezerapp.network

import com.creassrpm.deezerapp.models.ArtistModel
import com.creassrpm.deezerapp.models.DeezerArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistsInterface {

    @GET("genre/{artists}/artists")
    fun postAllArtist(@Path("artists") artists: String): Call<DeezerArtistResponse>
    @GET("artist")
    fun postArtists(@Path("data") artistGenre: Int): Call<ArtistModel>
}