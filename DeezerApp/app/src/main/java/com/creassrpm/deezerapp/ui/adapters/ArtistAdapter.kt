package com.creassrpm.deezerapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.ArtistModel
import com.creassrpm.deezerapp.utility.ItemClickListener
import com.creassrpm.deezerapp.utility.loadWithPicasso
import kotlinx.android.synthetic.main.row_artist_details.view.*

class ArtistAdapter(val ctx: Context, val list: List<ArtistModel>, val listeneer: ItemClickListener<ArtistModel>) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_artist_details, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.itemView.ivArtist.loadWithPicasso(list[position].picture_medium)
        holder.itemView.tvArtist.text = list[position].name
        holder.itemView.containerArtist.setOnClickListener {

            listeneer.onClick(position, list[position])

        }

    }


    override fun getItemCount(): Int = list.size

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}