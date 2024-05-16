package com.example.youtubeapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.data.model.dto.Video
import com.example.youtubeapi.databinding.ItemSearchBinding

class SearchListAdapter(
    private val onClick: (Video) -> Unit,
) : ListAdapter<Video, SearchListAdapter.ViewHolder>(

    // ref: https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil.ItemCallback#public-constructors_1
    object : DiffUtil.ItemCallback<Video>() {

        // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템이 서로 같은지 비교
        // 권장 사항 : Item의 파라미터에 고유한 ID 값이 있는 경우, 이 메서드는 ID를 기준으로 동일성을 반환 해야함
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        // areContentsTheSame : 현재 리스트에 노출하고 있는 아이템과 새로운 아이템의 equals를 비교한다.
        // 역할 : 한 Item의 내용이 변경되었는지 감지 (ex 좋아요 state 감지)
        // 호출 경로 : areItemsTheSame 메서드가 true를 반환하는 경우
        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class ViewHolder(
        private val binding: ItemSearchBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) = with(binding) {
            val data = getItem(position)

            // ivVideoThumbnail.load(data)
            tvVideoTitle.setText(data.snippet.title)
            tvVideoCreator.setText(data.snippet.channelTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Test", getItem(position).toString())

        holder.onBind(position)
    }
}