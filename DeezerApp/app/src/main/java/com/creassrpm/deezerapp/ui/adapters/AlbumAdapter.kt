package com.creassrpm.deezerapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.AlbumModel
import com.creassrpm.deezerapp.utility.ItemClickListener
import com.creassrpm.deezerapp.utility.loadWithPicasso
import kotlinx.android.synthetic.main.row_frament_album_by_artist.view.*

class AlbumAdapter(val list: List<AlbumModel>, val listener:ItemClickListener<AlbumModel>):RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_frament_album_by_artist, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        holder.itemView.ivAlbumItem.loadWithPicasso(list[position].cover_medium)
        holder.itemView.tvAlbumName.text = list[position].title
        holder.itemView.tvAlbumDate.text = list[position].release_date
        holder.itemView.containerAlbum.setOnClickListener {

            listener.onClick(position, list[position])

        }

    }

    override fun getItemCount(): Int = list.size

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}