package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.youtubeapi.databinding.FragmentVideoDetailBinding
import com.example.youtubeapi.viewmodel.MainViewModel

class VideoDetailFragment : Fragment() {

    private val binding by lazy { FragmentVideoDetailBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기 버튼을 누르면 VideoDetailFragment가 사라지게 처리되고, 하단 탭 작동이 됨.
        binding.ivBackButton.setOnClickListener{
            binding.cl.visibility = android.view.View.GONE
        }

        binding.btVideo1Test.setOnClickListener {
            val videoId = "vRheHVDYpcY"
            binding.btVideo1Test.text = videoId
        }
        binding.btVideo2Test.setOnClickListener {
            val videoId = "gEV8T3541j8"
            binding.btVideo2Test.text = videoId
        }
    }
}