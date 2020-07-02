package com.samsung.hyunjaee.scopedstorage.common

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUri")
fun setImageUri(view: ImageView, uri: Uri?) {
    if (uri != null) {
        Glide.with(view).load(uri).apply(
            RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.NONE)
                .centerCrop()
                .override(view.width, view.height)
        ).transition(DrawableTransitionOptions.withCrossFade()).into(view)
    }
}