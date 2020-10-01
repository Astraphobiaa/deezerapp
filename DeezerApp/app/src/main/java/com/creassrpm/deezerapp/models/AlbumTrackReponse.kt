package com.creassrpm.deezerapp.models

import java.io.Serializable

data class AlbumTrackResponse(
    val data: List<TrackModel>,
    val total: Int
) : Serializable

data class TrackModel(
    val artist: Artist,
    val disk_number: Int,
    val duration: String,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: String,
    val isrc: String,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: String,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val track_position: Int,
    val type: String
) : Serializable

data class Artist(
    val id: String,
    val name: String,
    val tracklist: String,
    val type: String
) : Serializable