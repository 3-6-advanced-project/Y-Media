package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.FragmentHomeBinding
import com.example.youtubeapi.presentation.adapter.HomeRecyclerViewAdapter
import com.example.youtubeapi.presentation.adapter.VideoClickListener
import com.example.youtubeapi.presentation.adapter.decoration.ListItemDecoration
import com.example.youtubeapi.presentation.uistate.VideoState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), VideoClickListener {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory()
    }

    private val mostPopularVideoAdapter by lazy { HomeRecyclerViewAdapter(this) }
    private val categoryVideoAdapter by lazy { HomeRecyclerViewAdapter(this) }
    private val categoryChannelAdapter by lazy { HomeRecyclerViewAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerViewInit(rvMostPopularVideo)
            recyclerViewInit(rvCategoryVideo)
            recyclerViewInit(rvCategoryChannel)
            rvMostPopularVideo.adapter = mostPopularVideoAdapter
            rvCategoryVideo.adapter = categoryVideoAdapter
            rvCategoryChannel.adapter = categoryChannelAdapter
        }

        lifecycleScope.launch {
            viewModel.mostPopularVideos.collect {
                mostPopularVideoAdapter.itemsUpdate(it)
            }
        }

        lifecycleScope.launch {
            viewModel.channels.collect {
                /**
                 * channel to video state
                 * */

            }
        }
    }

    private fun recyclerViewInit(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recyclerView.addItemDecoration(
            ListItemDecoration(resources.displayMetrics.density).apply {
                setPaddingValues(endDp = 8)
            }
        )
    }

    override fun onClick(videoState: VideoState) {

    }
}