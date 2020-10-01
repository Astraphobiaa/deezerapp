package com.creassrpm.deezerapp.network

import com.creassrpm.deezerapp.models.AlbumTrackResponse
import com.creassrpm.deezerapp.models.TrackModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackInterface {

    @GET("album/{albumid}/tracks")
    fun postAllTracks(@Path("albumid") track: String): Call<AlbumTrackResponse>
    @GET("Track")
    fun postTrack(@Path("track")trackByAlbum:Int): Call<TrackModel>

}