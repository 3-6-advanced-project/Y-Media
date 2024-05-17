package com.example.youtubeapi.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.SearchListAdapter
import com.example.youtubeapi.data.local.AppDatabase
import com.example.youtubeapi.databinding.FragmentSearchBinding
import com.example.youtubeapi.presentation.adapter.SearchFilterAdapter
import com.example.youtubeapi.presentation.adapter.decoration.ListItemDecoration
import com.example.youtubeapi.viewmodel.LatestNewsUiState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

    private val db by lazy { AppDatabase.getInstance(requireContext())!! }

    private val viewModel: MainViewModel by activityViewModels() {
        MainViewModelFactory(db.videoDao())
    }

    // TODO : 이후 VideoDetailFragment()의 companion object{}에서 parameter를 받도록 수정되면 videoId값 넘겨줘야함
    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter() { videoId ->
            showDetailFragment(videoId)
        }
    }

    private val searchFilterAdapter: SearchFilterAdapter by lazy {
        SearchFilterAdapter(
            listOf("Any", "Less than 4 minutes", "4 to 20 minutes", "more than 20 minutes")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditTextDoneActionListener()
        initFilterItems()
        initVideoItems()
        initViewModel()
    }

    private fun showDetailFragment(videoId: String) {
        // TODO : VideoDetailFragment.newInstance(videoId)로 수정 필요
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl, VideoDetailFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun initEditTextDoneActionListener() = with(binding) {
        etSearchKeyword.setOnEditorActionListener { v, actionId, event ->
            if (actionId != EditorInfo.IME_ACTION_DONE) {
                false
            }

            // 키보드 내리기
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().window.decorView.applicationWindowToken, 0)

            val filterTypes = listOf("any", "short", "medium", "short")
            viewModel.onSearch(
                query = etSearchKeyword.text.toString(),
                videoDuration = filterTypes[searchFilterAdapter.currentSelectPosition]
            )

            true
        }
    }

    private fun initFilterItems() = with(binding.rvSearchFilter) {
        adapter = searchFilterAdapter

        addItemDecoration(
            ListItemDecoration(resources.displayMetrics.density).apply {
                setPaddingValues(endDp = 10)
            }
        )
    }


    private fun initVideoItems() = with(binding.rvSearch) {
        adapter = searchListAdapter
        layoutManager = LinearLayoutManager(requireContext())

        addItemDecoration(
            ListItemDecoration(resources.displayMetrics.density).apply {
                setPaddingValues(bottomDp = 16)
            }
        )
    }


    private fun initViewModel() = lifecycleScope.launch {
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is LatestNewsUiState.Success -> {
                    val videoStates = uiState.videoStates

                    searchListAdapter.submitList(videoStates.toMutableList())
                    switchVisibility(videoStates.size)

                }

                is LatestNewsUiState.Error -> showErrorSnackbar(uiState)
            }
        }
    }

    private fun switchVisibility(size: Int) = with(binding) {
        if (size > 0) {
            tvNoData.visibility = View.GONE
            rvSearch.visibility = View.VISIBLE
        } else {
            rvSearch.visibility = View.GONE
            tvNoData.visibility = View.VISIBLE
        }
    }

    private fun showErrorSnackbar(uiState: LatestNewsUiState.Error) = with(binding) {
        Snackbar.make(requireContext(), binding.root, "Search Error..", Snackbar.LENGTH_SHORT)
            .apply {
                anchorView = binding.rvSearch
            }.show()

        Log.e("Search API Error", uiState.exception.message.toString())
    }
}