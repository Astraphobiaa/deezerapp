package com.creassrpm.deezerapp.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.*
import com.creassrpm.deezerapp.network.AlbumInterface
import com.creassrpm.deezerapp.network.DeezerClient
import com.creassrpm.deezerapp.network.RelatedArtistInterface
import com.creassrpm.deezerapp.ui.album.fragments.AlbumByArtistFragment
import com.creassrpm.deezerapp.ui.album.fragments.RelatedArtistFragment
import com.creassrpm.deezerapp.utility.loadWithPicasso
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_album.*

class AlbumActivity : AppCompatActivity() {

    lateinit var albumService: AlbumInterface
    lateinit var artistModel : ArtistModel
    lateinit var relatedService : RelatedArtistInterface

    companion object {
        const val pageTag = "AlbumActivity"
        var instance: AlbumActivity? = null
            get() {
                if (field == null)
                    field =
                        AlbumActivity()
                return field
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        //DelayAdded

        instance = this

        albumService = DeezerClient.getClient().create(AlbumInterface::class.java)
        relatedService = DeezerClient.getClient().create(RelatedArtistInterface::class.java)

        //ViewPager Adapter for Tabs

        vpAlbums.adapter = AlbumViewPagerAdapter(this)
        TabLayoutMediator(tabsAlbum, vpAlbums) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Album"
                }
                1 -> {
                    tab.text ="Related"
                }
            }

        }.attach()

        // Get Artist Model from ArtistActivity
        // Set ArtistModel to Fragments

        intent.getSerializableExtra("ArtistModel")?.let {
            artistModel = it as ArtistModel

            ivAlbumArtist.loadWithPicasso(artistModel.picture_xl)
            AlbumByArtistFragment.instance!!.artistModel = artistModel
            RelatedArtistFragment.instance!!.artistModel = artistModel

        }

       tvToolbarTtle.text = artistModel.name

    }
}