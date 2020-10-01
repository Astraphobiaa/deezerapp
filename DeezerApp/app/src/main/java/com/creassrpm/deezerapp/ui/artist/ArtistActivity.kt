package com.creassrpm.deezerapp.ui.artist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.ArtistModel
import com.creassrpm.deezerapp.models.GenreModel
import com.creassrpm.deezerapp.models.DeezerArtistResponse
import com.creassrpm.deezerapp.network.ArtistsInterface
import com.creassrpm.deezerapp.network.DeezerClient
import com.creassrpm.deezerapp.ui.adapters.ArtistAdapter
import com.creassrpm.deezerapp.ui.album.AlbumActivity
import com.creassrpm.deezerapp.utility.ItemClickListener
import kotlinx.android.synthetic.main.activity_artist_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArtistActivity : AppCompatActivity(), ItemClickListener<ArtistModel> {

    lateinit var artistService: ArtistsInterface
    lateinit var listArtist: DeezerArtistResponse
    lateinit var genreModel: GenreModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_details)

        artistService = DeezerClient.getClient().create(ArtistsInterface::class.java)

        //Get Model From MainActivity

        intent.getSerializableExtra("GenreModel")?.let {
            genreModel = it as GenreModel

            postAllArtist()

            tvToolbarTitle.text = genreModel.name

        }
    }

    // Get all artist by Genre.ID

    fun postAllArtist() {
        val postArtist = artistService.postAllArtist(genreModel.id)
        postArtist.enqueue(object : Callback<DeezerArtistResponse> {

            override fun onResponse(
                call: Call<DeezerArtistResponse>,
                response: Response<DeezerArtistResponse>
            ) {

                listArtist = response.body()!!

                val adapter = ArtistAdapter(this@ArtistActivity, listArtist.data, object : ItemClickListener<ArtistModel> {
                    override fun onClick(position: Int, item: ArtistModel) {

                        val intent = Intent(this@ArtistActivity,AlbumActivity::class.java)
                        intent.putExtra("ArtistModel",item)
                        startActivity(intent)

                    }
                })

                rvArtst.adapter = adapter

            }

            override fun onFailure(call: Call<DeezerArtistResponse>, t: Throwable) {

            }


        })


    }
}