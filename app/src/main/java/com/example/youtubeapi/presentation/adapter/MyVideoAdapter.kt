package com.example.youtubeapi.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtubeapi.data.model.entity.VideoEntity
import com.example.youtubeapi.databinding.ItemsGridBinding

class MyVideoAdapter(
    // val myVideo: MutableList<VideoEntity>,
    val itemClickListener: (String) -> Unit = {},
) : RecyclerView.Adapter<MyVideoAdapter.MViewHolder>() {

    private val myVideo = mutableListOf<VideoEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val binding = ItemsGridBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        Log.e("URGENT_TAG", "onBindViewHolder: ", )
        val dataSet = myVideo[position]

        holder.thumbnail.load(dataSet.thumbnailUrl)
        holder.thumbnail.setOnClickListener {
            itemClickListener(dataSet.videoId)
        }
        // holder.duration.text = dataSet.duration
        holder.title.text = dataSet.title
        holder.channel.text = dataSet.channelTitle
    }

//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }

    override fun getItemCount(): Int {
        return myVideo.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<VideoEntity>) {
        myVideo.clear()
        myVideo.addAll(items)
        notifyDataSetChanged()
        Log.e("URGENT_TAG", "updateItems: ", )
    }

    inner class MViewHolder(
        val binding: ItemsGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val thumbnail = binding.mvRvThumbnail
        // val duration = binding.mvRvDuration
        val title = binding.mvRvTitle
        val channel = binding.mvRvChannel
    }
}
