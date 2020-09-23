package com.thechefz.chefzlibrary.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.showImage(url:String){
    Glide.with(this).load(url).into(this)
}