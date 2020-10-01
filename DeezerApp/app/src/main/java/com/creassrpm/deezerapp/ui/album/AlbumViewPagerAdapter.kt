package com.creassrpm.deezerapp.ui.album

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.creassrpm.deezerapp.ui.album.fragments.AlbumByArtistFragment
import com.creassrpm.deezerapp.ui.album.fragments.RelatedArtistFragment

class AlbumViewPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {

    //Create tab from Int count

    override fun getItemCount(): Int = 2

    //Create Fragment with Tab Position

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> AlbumByArtistFragment()
            else -> RelatedArtistFragment()
        }
    }
}