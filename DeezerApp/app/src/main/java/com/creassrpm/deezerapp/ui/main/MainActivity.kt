package com.creassrpm.deezerapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.creassrpm.deezerapp.network.DeezerClient
import com.creassrpm.deezerapp.network.GenreInterface
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.GenreModel
import com.creassrpm.deezerapp.models.DeezerGenreResponse
import com.creassrpm.deezerapp.ui.adapters.GenreAdapter
import com.creassrpm.deezerapp.ui.artist.ArtistActivity
import com.creassrpm.deezerapp.utility.ItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ItemClickListener<GenreModel> {

    lateinit var genreService: GenreInterface
    lateinit var listGenre: DeezerGenreResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        genreService = DeezerClient.getClient().create(GenreInterface::class.java)
        getGenreAll()


    }

    // Get Response From Genre Service

    fun getGenreAll() {
        val post = genreService.postAllGenre()
        post.enqueue(object : Callback<DeezerGenreResponse> {

            override fun onResponse(
                call: Call<DeezerGenreResponse>,
                response: Response<DeezerGenreResponse>
            ) {

                listGenre = response.body()!!

                val adapter = GenreAdapter(this@MainActivity, listGenre.data, object : ItemClickListener<GenreModel> {
                    override fun onClick(position: Int, item: GenreModel) {

                        val intent = Intent(this@MainActivity, ArtistActivity::class.java)
                        intent.putExtra("GenreModel",item)
                        startActivity(intent)

                    }
                })

                deezer_recycler.adapter = adapter

            }

            override fun onFailure(call: Call<DeezerGenreResponse>, t: Throwable) {

            }


        })
    }


    fun getArtistById(id: Int) {
        val post = genreService.postGenre(id)
        post.enqueue(object : Callback<GenreModel> {

            override fun onResponse(call: Call<GenreModel>, response: Response<GenreModel>) {


            }

            override fun onFailure(call: Call<GenreModel>, t: Throwable) {

            }


        })
    }

}
