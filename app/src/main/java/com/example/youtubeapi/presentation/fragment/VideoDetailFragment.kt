package com.example.youtubeapi.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.youtubeapi.databinding.FragmentVideoDetailBinding
import com.example.youtubeapi.viewmodel.MainViewModel

private const val ARG_PARAM1 = "param1"
class VideoDetailFragment : Fragment() {

    private val binding by lazy { FragmentVideoDetailBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         *
         * */
        binding.btVideo1Test.setOnClickListener {
            val videoId = "vRheHVDYpcY"
            binding.btVideo1Test.text = videoId
        }
        binding.btVideo2Test.setOnClickListener {
            val videoId = "gEV8T3541j8"
            binding.btVideo2Test.text = videoId
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            // [1] Activity -> FirstFragment
            VideoDetailFragment().apply {
                arguments = Bundle().apply { //arguments에 받아져 있음.
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}