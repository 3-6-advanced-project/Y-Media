package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.SearchListAdapter
import com.example.youtubeapi.data.local.AppDatabase
import com.example.youtubeapi.data.model.dto.Channel
import com.example.youtubeapi.databinding.FragmentHomeBinding
import com.example.youtubeapi.databinding.FragmentSearchBinding
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


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _mostPopularVideoAdapter: HomeVideoRecyclerViewAdapter? = null
    private val mostPopularVideoAdapter get() = _mostPopularVideoAdapter!!

    private var _categoryVideoAdapter: HomeVideoRecyclerViewAdapter? = null
    private val categoryVideoAdapter get() = _categoryVideoAdapter!!

    private var _categoryChannelAdapter: HomeChannelRecyclerViewAdapter? = null
    private val categoryChannelAdapter get() = _categoryChannelAdapter!!

    private val db by lazy { AppDatabase.getInstance(requireContext())!! }
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(db.videoDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _mostPopularVideoAdapter = HomeVideoRecyclerViewAdapter(this)
        _categoryVideoAdapter = HomeVideoRecyclerViewAdapter(this)
        _categoryChannelAdapter = HomeChannelRecyclerViewAdapter(this)

        with(binding) {
            recyclerViewInit(rvMostPopularVideo)
            recyclerViewInit(rvCategoryVideo)
            recyclerViewInit(rvCategoryChannel)
            rvMostPopularVideo.adapter = mostPopularVideoAdapter
            rvCategoryVideo.adapter = categoryVideoAdapter
            rvCategoryChannel.adapter = categoryChannelAdapter
            ivMenuButton.setOnClickListener(menuButtonOnClickListener)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mostPopularVideos.collect {
                mostPopularVideoAdapter.itemsUpdate(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mostPopularVideoWithCategory.collect {
                categoryVideoAdapter.itemsUpdate(it)
                binding.rvCategoryVideo.scrollToPosition(0)

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.channels.collect {
                categoryChannelAdapter.itemsUpdate(it)
                binding.rvCategoryChannel.scrollToPosition(0)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentCategory.collect {
                it?.let { nonNull ->
                    binding.tvCurrentCategory.text = nonNull.snippet.title
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _mostPopularVideoAdapter = null
        _categoryVideoAdapter = null
        _categoryChannelAdapter = null
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
        showDetailFragment(videoState.id)
    }
    override fun onChannelClick(channel: Channel) {

    }
    private fun showDetailFragment(videoId: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl, VideoDetailFragment.newInstance(videoId))
            .addToBackStack(null)
            .commit()
    }
}