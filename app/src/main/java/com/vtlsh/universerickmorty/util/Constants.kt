package com.vtlsh.universerickmorty.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.vtlsh.universerickmorty.R
import com.bumptech.glide.request.target.Target

class Constants {

    companion object {
        val API_URL = "https://rickandmortyapi.com/"
    }

}

fun ImageView.load(url: String, loadOnlyFromCache: Boolean = false, onLoadingFinished: () -> Unit = {}) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            onLoadingFinished()
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onLoadingFinished()
            return false
        }
    }

    val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_default_icon)
        .dontTransform()
        .onlyRetrieveFromCache(loadOnlyFromCache)

    Glide.with(this)
        .load(url)
        .centerCrop()
        .apply(requestOptions)
        .listener(listener)
        .into(this)
}

fun getEpisode(episodeUrl: String?): String {
    return episodeUrl?.substringAfterLast("/") ?: ""
}