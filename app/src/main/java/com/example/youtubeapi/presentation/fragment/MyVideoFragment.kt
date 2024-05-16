package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.youtubeapi.R
import com.example.youtubeapi.bookmarkDummy
import com.example.youtubeapi.data.model.entity.VideoEntity
import com.example.youtubeapi.databinding.FragmentMyVideoBinding
import com.example.youtubeapi.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MyVideoFragment : Fragment() {

    private val binding by lazy { FragmentMyVideoBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
