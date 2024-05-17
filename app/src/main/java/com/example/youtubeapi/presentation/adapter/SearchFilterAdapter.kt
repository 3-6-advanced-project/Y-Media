package com.example.youtubeapi.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemSearchFilterBinding

class SearchFilterAdapter(
    private val types: List<String>
): RecyclerView.Adapter<SearchFilterAdapter.Holder>()
{
    private var currentSelectPosition = 0

    inner class Holder(
        private val binding: ItemSearchFilterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) = with(binding) {
            tvVideoDuration.text = types[position]

            // 그냥 false로 정의하면 처음에 Any도 선택되어 있지 않음
            tvVideoDuration.isSelected = (currentSelectPosition == position)

            tvVideoDuration.setOnClickListener {
                // 선택된 Item과 다른 Item 클릭 시 호출
                if (currentSelectPosition != position) {
                    // 새롭게 선택된 TextView의 isSelected 속성 true
                    it.isSelected = true

                    // 전에 선택됐던 TextView의 isSelected 속성 false
                    notifyItemChanged(currentSelectPosition) // -> onBind 재호출

                    // 새롭게 선택된 position값 저장
                    currentSelectPosition = position
                }

                // 선택된 Item을 다시 한 번 더 클릭 했을 때 -> Item 해제 및 "Any"로 이동
                else {
                    // 전에 선택됐던 TextView의 isSelected 속성 false
                    notifyItemChanged(currentSelectPosition)

                    it.isSelected = true
                    currentSelectPosition = 0
                    notifyItemChanged(currentSelectPosition)
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