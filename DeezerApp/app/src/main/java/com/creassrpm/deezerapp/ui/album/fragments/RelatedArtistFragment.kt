package com.creassrpm.deezerapp.ui.album.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.*
import com.creassrpm.deezerapp.network.DeezerClient
import com.creassrpm.deezerapp.network.RelatedArtistInterface
import com.creassrpm.deezerapp.ui.adapters.RelatedArtistAdapter
import com.creassrpm.deezerapp.ui.album.AlbumActivity
import com.creassrpm.deezerapp.utility.ItemClickListener
import kotlinx.android.synthetic.main.fragment_related_artist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RelatedArtistFragment : Fragment() {

    lateinit var relatedService: RelatedArtistInterface
    lateinit var listRelated: DeezerArtistResponse
    lateinit var artistModel : ArtistModel
    companion object {
        const val pageTag = "RelatedArtistFragment"
        var instance: RelatedArtistFragment? = null
            get() {
                if (field == null)
                    field =
                        RelatedArtistFragment()
                return field
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_related_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instance = this

        relatedService = DeezerClient.getClient().create(RelatedArtistInterface::class.java)
        this.artistModel = AlbumActivity.instance!!.artistModel
        getRelated(artistModel,relatedService)

    }

    // Get Related Artists from ArtistModel.Related
    // Example API Link : "https://api.deezer.com/artist/{artistID}/related"

    fun getRelated(relatedModel: ArtistModel, relatedService: RelatedArtistInterface) {

        val post = this.relatedService.postAllRelated(relatedModel.id)
        post.enqueue(object : Callback<DeezerArtistResponse> {
            override fun onResponse(
                call: Call<DeezerArtistResponse>,
                response: Response<DeezerArtistResponse>
            ) {

                listRelated = response.body()!!

                val adapter = RelatedArtistAdapter(listRelated.data, object : ItemClickListener<ArtistModel> {
                        override fun onClick(position: Int, item: ArtistModel) {
                            super.onClick(position, item)
                            val intent = Intent(requireContext(),AlbumActivity::class.java).putExtra("ArtistModel",item)
                            startActivity(intent)
                        }
                    })
                rvRelatedArtistt.adapter = adapter

            }

            override fun onFailure(call: Call<DeezerArtistResponse>, t: Throwable) {


            }

        })

    }


}