package com.creassrpm.deezerapp.network


import com.creassrpm.deezerapp.models.AlbumResponse
import com.creassrpm.deezerapp.models.ArtistAlbumResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumInterface {

    @GET("artist/{artistid}/albums")
    fun postAllAlbum(@Path("artistid") artist: String): Call<AlbumResponse>
    @GET("artist")
    fun postArtists(@Path("data") artistGenre: Int): Call<ArtistAlbumResponse>

}