package com.gultendogan.foodapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gultendogan.foodapp.R

fun ImageView.imageLoad(url : String?, placeholder : CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeholder).error(R.drawable.ic_baseline_warning_24)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun makePlaceholder(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth= 8f
        centerRadius= 40f
        start()
    }
}