package com.example.youtubeapi.presentation.fragment

import android.content.Context
import android.os.Bundle
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
import com.example.youtubeapi.presentation.adapter.decoration.ListItemDecoration
import com.example.youtubeapi.viewmodel.LatestNewsUiState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

    private val db by lazy { AppDatabase.getInstance(requireContext())!! }

    private val viewModel: MainViewModel by activityViewModels() {
        MainViewModelFactory(db.videoDao())
    }

    private val searchListAdapter = SearchListAdapter() { video ->
        // TODO : 이후 VideoDetailFragment()의 companion object{}에서 parameter를 받도록 수정되면 videoId값 넘겨줘야함
        requireActivity().supportFragmentManager.beginTransaction()
            // .replace(R.id.fl_item, VideoDetailFragment.newInstance(video.id))
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

        initEditTextDoneActionListener()
        initRecyclerView()
        initViewModel()
    }

    private fun initEditTextDoneActionListener() = with(binding) {
        etSearchKeyword.setOnEditorActionListener { v, actionId, event ->
            var handled = false

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // 키보드 내리기
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireActivity().window.decorView.applicationWindowToken, 0)

                viewModel.onSearch(etSearchKeyword.text.toString())
                handled = true
            }

            handled
        }
    }


    private fun initRecyclerView() = with(binding.rvSearch) {
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
                }
                is LatestNewsUiState.Error -> initRVItem()
            }
        }
    }

    private fun initRVItem() = with(binding) {

    }
}