package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.SearchListAdapter
import com.example.youtubeapi.databinding.FragmentSearchBinding
import com.example.youtubeapi.viewmodel.LatestNewsUiState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels() {
        MainViewModelFactory()
    }

    private val searchListAdapter = SearchListAdapter() { video ->
        val videoId = video.id.videoId

        // TODO : 이후 VideoDetailFragment()의 companion object{}에서 parameter를 받도록 수정되면 videoId값 넘겨줘야함
        requireActivity().supportFragmentManager.beginTransaction()
            // .replace(R.id.ll_top, VideoDetailFragment.newInstance(videoId))
            .replace(R.id.ll_top, VideoDetailFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onSearch("cat")

        with(binding.rvSearch) {
            adapter = searchListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is LatestNewsUiState.Success -> {
                        searchListAdapter.submitList(uiState.videoResponse!!.items)
                    }

                    is LatestNewsUiState.Error -> initRVItem()
                }
            }

        }
    }

    private fun initRVItem() = with(binding) {

    }
}