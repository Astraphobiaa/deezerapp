package com.creassrpm.deezerapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.ArtistModel
import com.creassrpm.deezerapp.utility.ItemClickListener
import com.creassrpm.deezerapp.utility.loadWithPicasso
import kotlinx.android.synthetic.main.row_fragment_related_artist.view.*

class RelatedArtistAdapter(val list: List<ArtistModel>, val listener: ItemClickListener<ArtistModel>): RecyclerView.Adapter<RelatedArtistAdapter.RelatedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_fragment_related_artist,parent,false)
        return RelatedViewHolder(view)

    }

    override fun onBindViewHolder(holder: RelatedViewHolder, position: Int) {

        holder.itemView.ivRelatedArtist.loadWithPicasso(list[position].picture_medium)
        holder.itemView.tvRelatedArtist.text = list[position].name
        holder.itemView.containerRelated.setOnClickListener {

            listener.onClick(position,list[position])

        }


    }

    override fun getItemCount(): Int = list.size


    class RelatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}