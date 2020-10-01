package com.creassrpm.deezerapp.ui.track

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.AlbumModel
import com.creassrpm.deezerapp.models.AlbumTrackResponse
import com.creassrpm.deezerapp.models.ArtistModel
import com.creassrpm.deezerapp.models.TrackModel
import com.creassrpm.deezerapp.network.DeezerClient
import com.creassrpm.deezerapp.network.TrackInterface
import com.creassrpm.deezerapp.ui.adapters.TrackAdapter
import com.creassrpm.deezerapp.utility.ItemClickListener
import kotlinx.android.synthetic.main.activity_track.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackActivity : AppCompatActivity() {

    lateinit var trackService: TrackInterface
    lateinit var listTrack: AlbumTrackResponse
    lateinit var albumModel: AlbumModel
    lateinit var artistModel: ArtistModel
    var currentPosition = 0
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        trackService = DeezerClient.getClient().create(TrackInterface::class.java)

        // Get Album from AlbumByArtistFragment

        intent.getSerializableExtra("AlbumModel")?.let {
            albumModel = it as AlbumModel

        }

        tvToolbarTitle.text = albumModel.title
        postAllTracks()
        clickListener()

    }

    // Media Player ClickListeners

    fun clickListener() {

        //Change Play/Pause icon & start/pause current track with If controller

        ivPlay.setOnClickListener {

            mediaPlayer?.let {
                if (mediaPlayer!!.isPlaying) {
                    ivPlay?.setImageDrawable(getDrawable(R.drawable.playbutton))
                    mediaPlayer!!.pause()
                    super.onPause()

                } else {
                    ivPlay?.setImageDrawable(getDrawable(R.drawable.pausebutton))
                    mediaPlayer!!.start()
                }
            }
        }

        // Next Track

        ivNext.setOnClickListener {
            currentPosition++
            if (currentPosition < listTrack.data.size) {
                playMediaPlayer(listTrack.data[currentPosition].preview)
            }
            else{
                currentPosition--
            }
        }

        // Previous Track

        ivPrevious.setOnClickListener {
            currentPosition--
            if (currentPosition >= 0) {
                playMediaPlayer(listTrack.data[currentPosition].preview)
            }
            else{
                currentPosition++
            }
        }

        // Volume control with Seekbar & sb.VisibleOrGone

        ivVolume.setOnClickListener {

            if (flSeekbar.visibility == View.VISIBLE)
                flSeekbar.visibility = View.GONE
            else
                flSeekbar.visibility = View.VISIBLE
        }

        // Seekbar Progress Control

        sbVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                mediaPlayer?.let {
                    it.setVolume(p1.toFloat(), p1.toFloat())
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    // Post All Tracks by Album.ID
    // Example API Link :"https://api.deezer.com/album/{albumID}/tracks"

    fun postAllTracks() {

        val postTrack = trackService.postAllTracks(albumModel.id)
        postTrack.enqueue(object : Callback<AlbumTrackResponse> {


            override fun onResponse(
                call: Call<AlbumTrackResponse>,
                response: Response<AlbumTrackResponse>
            ) {

                listTrack = response.body()!!

                val adapter = TrackAdapter(
                    this@TrackActivity,
                    listTrack.data,
                    albumModel.cover,
                    object : ItemClickListener<TrackModel> {

                        override fun onClick(position: Int, item: TrackModel) {
                            super.onClick(position, item)

                            currentPosition = position
                            playMediaPlayer(item.preview)
                        }


                    })


                rvTrack.adapter = adapter


            }

            override fun onFailure(call: Call<AlbumTrackResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    // Media Player Fun

    fun playMediaPlayer(url: String) {

        try {
            val uri = Uri.parse(url)
            when {
                mediaPlayer == null -> {
                    mediaPlayer = MediaPlayer()
                }
                mediaPlayer!!.isPlaying -> {
                    mediaPlayer!!.reset()
                }
                else -> {
                    mediaPlayer =MediaPlayer()
                }
            }

            // Media Player will get tracks from uri

            mediaPlayer!!.setDataSource(this, uri)
            mediaPlayer!!.prepare()
            if (mediaPlayer!!.isPlaying) {
                ivPlay?.setImageDrawable(getDrawable(R.drawable.playbutton))
                mediaPlayer!!.pause()

            } else {
                ivPlay?.setImageDrawable(getDrawable(R.drawable.pausebutton))
                mediaPlayer!!.start()
            }
        } catch (e: Exception) {
            var toast = Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    // When you press "Back" button from your phone, this method will stop current playing track. Implemented to pause method.

    override fun onBackPressed() {
        if (mediaPlayer != null)
        mediaPlayer?.stop()
        super.onBackPressed()
    }
}