package com.example.youtubeapi.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.youtubeapi.data.model.dto.Channel
import com.example.youtubeapi.databinding.ItemHomeFragmentChannelBinding
import com.example.youtubeapi.databinding.ItemHomeFragmentVideoBinding
import com.example.youtubeapi.presentation.uistate.VideoState

class HomeChannelRecyclerViewAdapter(
    private val fragment: Fragment
): RecyclerView.Adapter<HomeChannelRecyclerViewAdapter.VideoViewHolder>() {

    private val itemList = mutableListOf<Channel>()
    private var channelClickListener: ChannelClickListener? = null

    init {
        if(fragment is ChannelClickListener) {
            channelClickListener = fragment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemHomeFragmentChannelBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val current = itemList[position]
        with(holder.binding) {
            ivChannelImage.load(current.snippet.thumbnails.high.url)
            tvChannelTitle.text = current.snippet.title
            root.setOnClickListener {
                channelClickListener?.onChannelClick(current)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun itemsUpdate(items: List<Channel>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(
        val binding: ItemHomeFragmentChannelBinding
    ): ViewHolder(binding.root)
}

interface ChannelClickListener {
    fun onChannelClick(channel: Channel)
}

