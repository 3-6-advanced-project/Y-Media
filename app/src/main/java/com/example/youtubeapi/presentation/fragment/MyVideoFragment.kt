package com.example.youtubeapi.presentation.fragment

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.MyVideoAdapter
import com.example.youtubeapi.data.local.AppDatabase
import com.example.youtubeapi.databinding.FragmentMyVideoBinding
import com.example.youtubeapi.dummyData
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MyVideoFragment : Fragment() {

    private val binding by lazy { FragmentMyVideoBinding.inflate(layoutInflater) }
    private val db by lazy { AppDatabase.getInstance(requireContext())!! }
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(db.videoDao())
    }

    private val mAdapter by lazy {
        MyVideoAdapter { videoId ->
            showDetailFragment(videoId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mvRvLiked.apply {
            layoutManager = GridLayoutManager(activity, 2)
            this.adapter = mAdapter
            this.addItemDecoration(GridSpacingItemDecoration(
                2,
                (16 * resources.displayMetrics.density + 0.5f).toInt(),
                false)
            )
        }

        lifecycleScope.launch {
            viewModel.bookmarks.collect {
                Log.e("URGENT_TAG", "MyVideoFragment: onViewCreated: called")
                mAdapter.updateItems(it)
            }
        }

//        binding.mvRvLiked.addItemDecoration(
//            ListItemDecoration(resources.displayMetrics.density).apply {
//                setPaddingValues(startDp = 5, bottomDp = 10)
//            }
//        )

//        binding.mvRvLiked.addItemDecoration(
//            GridSpacingItemDecoration(2, 1f.fromDpToPx(), true)
//        )
    }

    private fun showDetailFragment(videoId: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl, VideoDetailFragment.newInstance(videoId))
            .addToBackStack(null)
            .commit()
    }

    internal class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {

            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column
            if (includeEdge) {
                outRect.left =
                    spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right =
                    (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left =
                    column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                    spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    private fun Float.fromDpToPx(): Int =
        (this * Resources.getSystem().displayMetrics.density).toInt()

}
