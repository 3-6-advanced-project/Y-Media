package com.example.youtubeapi.presentation.adapter.decoration

import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HomeItemDecoration: ItemDecoration() {

    private var paddingValue: Int = 16

    fun setOffset(paddingValue: Int) {
        this.paddingValue = paddingValue
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            right = paddingValue
        }
    }
}