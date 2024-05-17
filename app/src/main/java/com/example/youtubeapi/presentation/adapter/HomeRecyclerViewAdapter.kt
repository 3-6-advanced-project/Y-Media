package com.example.youtubeapi.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.youtubeapi.databinding.ItemHomeFragmentVideoBinding
import com.example.youtubeapi.presentation.uistate.VideoState

class HomeRecyclerViewAdapter(
    private val fragment: Fragment
): RecyclerView.Adapter<HomeRecyclerViewAdapter.VideoViewHolder>() {

    private val itemList = mutableListOf<VideoState>()
    private var videoClickListener: VideoClickListener? = null

    init {
        if(fragment is VideoClickListener) {
            videoClickListener = fragment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemHomeFragmentVideoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val current = itemList[position]
        with(holder.binding) {
            ivVideo.load(current.thumbnail.url)
            tvTitle.text = current.title
            tvDescription.text = current.description
            root.setOnClickListener {
                videoClickListener?.onClick(current)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun itemsUpdate(items: List<VideoState>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(
        val binding: ItemHomeFragmentVideoBinding
    ): ViewHolder(binding.root)
}

interface VideoClickListener {
    fun onClick(videoState: VideoState)
}
