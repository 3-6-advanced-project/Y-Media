package com.example.youtubeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtubeapi.data.model.entity.VideoEntity
import com.example.youtubeapi.databinding.ItemsGridBinding

class MyVideoAdapter(val myVideo: MutableList<VideoEntity>) : RecyclerView.Adapter<MyVideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataSet = myVideo[position]
        holder.thumbnail.load(dataSet.thumbnailUrl)
        holder.duration.text = dataSet.duration
        holder.title.text = dataSet.title
        holder.channel.text = dataSet.channelTitle
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return myVideo.size
    }

    inner class ViewHolder(private val binding: ItemsGridBinding) : RecyclerView.ViewHolder(binding.root) {
        val thumbnail = binding.mvRvThumbnail
        val duration = binding.mvRvDuration
        val title = binding.mvRvTitle
        val channel = binding.mvRvChannel
    }
}
