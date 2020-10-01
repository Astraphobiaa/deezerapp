package com.creassrpm.deezerapp.ui.album.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.AlbumModel
import com.creassrpm.deezerapp.models.AlbumResponse
import com.creassrpm.deezerapp.models.ArtistModel
import com.creassrpm.deezerapp.network.AlbumInterface
import com.creassrpm.deezerapp.network.DeezerClient
import com.creassrpm.deezerapp.ui.adapters.AlbumAdapter
import com.creassrpm.deezerapp.ui.album.AlbumActivity
import com.creassrpm.deezerapp.ui.track.TrackActivity
import com.creassrpm.deezerapp.utility.ItemClickListener
import kotlinx.android.synthetic.main.fragment_album_by_artist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumByArtistFragment : Fragment() {

    lateinit var albumService: AlbumInterface
    lateinit var listAlbum : AlbumResponse
    lateinit var artistModel : ArtistModel

    companion object {
        const val pageTag = "AlbumByArtistFragment"
        var instance: AlbumByArtistFragment? = null
            get() {
                if (field == null)
                    field =
                        AlbumByArtistFragment()
                return field
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_by_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumService = DeezerClient.getClient().create(AlbumInterface::class.java)
        //DelayAdded
        instance = this
        this.artistModel = AlbumActivity.instance!!.artistModel
        getAlbum(artistModel,albumService)

    }

    // Get All Album by Artist.ID

    fun getAlbum(artistModel: ArtistModel, albumService: AlbumInterface) {
       this. albumService = albumService
        val post = this.albumService.postAllAlbum(artistModel.id)
        post.enqueue(object : Callback<AlbumResponse> {

            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {

                listAlbum = response.body()!!

                val adapter = AlbumAdapter(listAlbum.data, object : ItemClickListener<AlbumModel> {
                    override fun onClick(position: Int, item: AlbumModel) {
                        super.onClick(position, item)

                        val intent = Intent(requireContext(),TrackActivity::class.java).putExtra("AlbumModel",item)
                        val intent2 = Intent(requireContext(),TrackActivity::class.java).putExtra("Cover",item.cover)
                        startActivity(intent)


                    }




                })
                rvAlbumByArtist.adapter = adapter
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {

            }


        })
    }

}