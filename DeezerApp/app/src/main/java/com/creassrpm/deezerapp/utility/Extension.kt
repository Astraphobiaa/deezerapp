package com.creassrpm.deezerapp.utility

import android.widget.ImageView
import com.squareup.picasso.Picasso

//ImageLibExtension

fun ImageView.loadWithPicasso(url:String){

    Picasso.get()
        .load(url)
        .into(this);

}