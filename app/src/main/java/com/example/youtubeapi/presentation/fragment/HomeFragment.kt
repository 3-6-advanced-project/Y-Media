package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.data.model.dto.Channel
import com.example.youtubeapi.databinding.FragmentHomeBinding
import com.example.youtubeapi.presentation.adapter.ChannelClickListener
import com.example.youtubeapi.presentation.adapter.HomeChannelRecyclerViewAdapter
import com.example.youtubeapi.presentation.adapter.HomeVideoRecyclerViewAdapter
import com.example.youtubeapi.presentation.adapter.VideoClickListener
import com.example.youtubeapi.presentation.adapter.decoration.ListItemDecoration
import com.example.youtubeapi.presentation.uistate.VideoState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), VideoClickListener, ChannelClickListener {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory()
    }

    private val mostPopularVideoAdapter by lazy { HomeVideoRecyclerViewAdapter(this) }
    private val categoryVideoAdapter by lazy { HomeVideoRecyclerViewAdapter(this) }
    private val categoryChannelAdapter by lazy { HomeChannelRecyclerViewAdapter(this) }

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
            ivMenuButton.setOnClickListener(menuButtonOnClickListener)
        }

        lifecycleScope.launch {
            viewModel.mostPopularVideos.collect {
                mostPopularVideoAdapter.itemsUpdate(it)
            }
        }

        lifecycleScope.launch {
            viewModel.mostPopularVideoWithCategory.collect {
                categoryVideoAdapter.itemsUpdate(it)
            }
        }

        lifecycleScope.launch {
            viewModel.channels.collect {
                categoryChannelAdapter.itemsUpdate(it)
            }
        }

        lifecycleScope.launch {
            viewModel.currentCategory.collect {
                it?.let { nonNull ->
                    binding.tvCurrentCategory.text = nonNull.snippet.title
                }
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

    private val menuButtonOnClickListener: (View) -> Unit = {
        val popupMenu = PopupMenu(context, it)


        viewModel.categories.value.forEach {
            popupMenu.menu.add(it.snippet.title)
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            val videoCategory = viewModel.categories.value.first {
                it.snippet.title == menuItem.title
            }
            viewModel.initCurrentCategory(videoCategory)
            true
        }

        popupMenu.show()
    }

    override fun onVideoClick(videoState: VideoState) {

    }
    override fun onChannelClick(channel: Channel) {

    }
}