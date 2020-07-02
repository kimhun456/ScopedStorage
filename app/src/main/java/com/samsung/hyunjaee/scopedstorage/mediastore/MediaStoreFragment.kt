package com.samsung.hyunjaee.scopedstorage.mediastore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.samsung.hyunjaee.scopedstorage.R
import com.samsung.hyunjaee.scopedstorage.databinding.FragmentMediaStoreBinding
import timber.log.Timber


class MediaStoreFragment : Fragment() {

    private lateinit var viewModel: MediaStoreViewModel
    private lateinit var binding: FragmentMediaStoreBinding

    companion object {

        @JvmStatic
        fun newInstance(): MediaStoreFragment {
            return MediaStoreFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
        viewModel = ViewModelProvider(this).get(MediaStoreViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_media_store,
            container,
            false
        )
        val adapter = MediaAdapter(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        viewModel.mediaList().observe(viewLifecycleOwner, Observer { mediaList ->
            adapter.submitList(mediaList)
        })
        binding.audioButton.setOnClickListener { viewModel.loadAudios() }
        binding.videoButton.setOnClickListener { viewModel.loadVideos() }
        binding.imageButton.setOnClickListener { viewModel.loadImages() }
        return binding.root
    }
}