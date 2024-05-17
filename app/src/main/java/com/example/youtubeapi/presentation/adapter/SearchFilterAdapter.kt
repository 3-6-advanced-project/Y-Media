package com.example.youtubeapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemSearchFilterBinding

data class TypeItem(
    val type: String,
    val isSelected: Boolean = false,
)

class SearchFilterAdapter(
    private val types: MutableList<TypeItem>
): RecyclerView.Adapter<SearchFilterAdapter.Holder>()
{
    private var currentSelectPosition = 0

    inner class Holder(
        private val binding: ItemSearchFilterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) = with(binding) {
            tvVideoDuration.text = types[position].type

            val isMatch = (currentSelectPosition == position)

            types[adapterPosition] = types[adapterPosition].copy(
                isSelected = isMatch
            )
            tvVideoDuration.isSelected = isMatch

            tvVideoDuration.setOnClickListener {
                if (currentSelectPosition != position) {
                    it.isSelected = true

                    // 전에 선택됐던 Item 갱신
                    notifyItemChanged(currentSelectPosition)
                    currentSelectPosition = position
                }
            }
        }
    }

    override fun getItemCount() = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemSearchFilterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(position)
    }
}