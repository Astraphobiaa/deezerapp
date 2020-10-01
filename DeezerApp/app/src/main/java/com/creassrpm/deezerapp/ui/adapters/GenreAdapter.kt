package com.creassrpm.deezerapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creassrpm.deezerapp.R
import com.creassrpm.deezerapp.models.GenreModel
import com.creassrpm.deezerapp.utility.ItemClickListener
import com.creassrpm.deezerapp.utility.loadWithPicasso
import kotlinx.android.synthetic.main.row_genre.view.*

class GenreAdapter(val ctx:Context, val list: List<GenreModel>, val listener: ItemClickListener<GenreModel>) : RecyclerView.Adapter<GenreAdapter.DeezerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeezerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_genre, parent, false)
        return DeezerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeezerViewHolder, position:Int){
        holder.itemView.ivGenre.loadWithPicasso(list[position].picture_medium)
        holder.itemView.tvGenre.text = list[position].name
        holder.itemView.containerGenre.setOnClickListener {
            

            listener.onClick(position,list[position])

        }
    }

    override fun getItemCount(): Int = list.size

    class DeezerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    }

}