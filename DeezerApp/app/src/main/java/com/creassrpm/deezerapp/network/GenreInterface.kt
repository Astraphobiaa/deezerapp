package com.creassrpm.deezerapp.network

import com.creassrpm.deezerapp.models.GenreModel
import com.creassrpm.deezerapp.models.DeezerGenreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GenreInterface {

        @GET("genre/{data}")
        fun postGenre(@Path("data") data: Int): Call<GenreModel>

        @GET("genre")
        fun postAllGenre(): Call<DeezerGenreResponse>


}