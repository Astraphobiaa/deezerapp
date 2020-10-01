package com.creassrpm.deezerapp.network

import com.creassrpm.deezerapp.models.DeezerArtistResponse
import com.creassrpm.deezerapp.models.RelatedArtistResponse
import com.creassrpm.deezerapp.models.RelatedArtistsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RelatedArtistInterface {

    @GET("artist/{relatedid}/related")
    fun postAllRelated(@Path("relatedid") related: String): Call<DeezerArtistResponse>
    @GET("Related")
    fun postRelated(@Path("related") relatedByGenre: Int): Call<RelatedArtistsModel>

}