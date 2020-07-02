package com.samsung.hyunjaee.scopedstorage.mediastore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samsung.hyunjaee.scopedstorage.R
import com.samsung.hyunjaee.scopedstorage.databinding.MediaItemBinding
import com.samsung.hyunjaee.scopedstorage.domain.entity.Media

class MediaAdapter(private val context: Context?) :
    RecyclerView.Adapter<MediaAdapter.MediaHolder>() {

    private val mediaList = mutableListOf<Media>()

    fun submitList(newList: List<Media>) {
        mediaList.clear()
        mediaList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaAdapter.MediaHolder {
        val binding: MediaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.media_item,
            parent,
            false
        )
        return MediaHolder(binding)
    }

    override fun getItemCount() = mediaList.size

    override fun onBindViewHolder(holder: MediaAdapter.MediaHolder, position: Int) {
        holder.binding.media = mediaList[position]
    }

    inner class MediaHolder(val binding: MediaItemBinding) : RecyclerView.ViewHolder(binding.root)
}