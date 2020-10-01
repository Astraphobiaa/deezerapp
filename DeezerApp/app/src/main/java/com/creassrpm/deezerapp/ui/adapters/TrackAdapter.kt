package com.creassrpm.deezerapp.ui.adapters

import android.content.Context
import android.icu.number.IntegerWidth
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.AlbumModel
import com.creassrpm.deezerapp.models.TrackModel
import com.creassrpm.deezerapp.utility.ItemClickListener
import com.creassrpm.deezerapp.utility.loadWithPicasso
import kotlinx.android.synthetic.main.activity_track.view.*
import kotlinx.android.synthetic.main.row_track.view.*

class TrackAdapter(val ctx:Context,val list:List<TrackModel>, val url : String, val listener:ItemClickListener<TrackModel>): RecyclerView.Adapter<TrackAdapter.TrackViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_track, parent,false)
        return TrackViewHolder(view)

    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {

        holder.itemView.tvTrackName.text = list[position].title
        // String to Min&Sec Format Casting
        val min =  Integer.parseInt( list[position].duration) / 60
        val sec =   Integer.parseInt( list[position].duration) -  (min*60)
        val timeString = String.format("%02d:%02d", min, sec,)
        holder.itemView.tvTrackDuration.text =timeString
        holder.itemView.ivTrackPhoto.loadWithPicasso(url)
        holder.itemView.containerTrack.setOnClickListener {

            listener.onClick(position, list[position])

        }


    }

    override fun getItemCount(): Int = list.size

    class TrackViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

}