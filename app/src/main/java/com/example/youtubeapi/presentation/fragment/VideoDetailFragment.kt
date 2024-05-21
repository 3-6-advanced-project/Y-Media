package com.example.youtubeapi.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.youtubeapi.R
import com.example.youtubeapi.data.local.AppDatabase
import com.example.youtubeapi.databinding.FragmentVideoDetailBinding
import com.example.youtubeapi.presentation.uistate.VideoState
import com.example.youtubeapi.viewmodel.LatestNewsUiState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"

class VideoDetailFragment : Fragment() {
    private var videoId: String? = null

    private var currentVideoState: VideoState? = null

    private val binding by lazy { FragmentVideoDetailBinding.inflate(layoutInflater) }
    private val db by lazy { AppDatabase.getInstance(requireContext())!! }
    private val viewModel: MainViewModel by activityViewModels { //뷰모델 초기화 시 입력값 설정이 없어서 생긴 문제.
        MainViewModelFactory(db.videoDao())
    }

    private lateinit var channelId: String //영상 채널 ID 저장 위치

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments.let { videoId = it!!.getString(ARG_PARAM1) }
        Log.d("video id: onAttach에서 arguments 이후", videoId.toString())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel() //데이터 바뀔때 자동 호출됨. coroutine

        binding.ivBackButton.setOnClickListener{
            binding.cl.visibility = android.view.View.GONE
        }

        val videoId = videoId.toString() //나중에는 다른 fragment에서 보낸 정보를 여기 연결.
        Log.d("video id: onViewCreated 처음 출력", videoId)

        checkVideoIcon(videoId)
        Log.d("video id: db에 존재하는지 여부 체크 직후", videoId)

        //arguments에서 가져온 videoId로 화면 정보 갱신
        viewModel.onDetail(videoId!!)
        Log.d("video id: viewModel.onDetail에 잘 들어왔는지", videoId)

        channelId = findChannelId(videoId!!) //videolId에서 추출하는 함수 필요하지 않아?
        viewModel.onDetailChannel(channelId!!)
        Log.d("channel id: viewModel.onDetailChannels에 잘 들어왔는지", channelId)

        binding.ivLikesButton.setOnClickListener {
            savedLikes(videoId!!) //좋아요가 저장되어있는지 확인하고, 없으면
        }

    }

    private fun findChannelId(videoId: String): String{
        val channelId = "UCCjHwLqUSjxB7NtvUK9fcmw" //TODO: 실제론 videoId에서 받아오기
        return channelId
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


    private fun initViewModel2() = lifecycleScope.launch {
        viewModel.uiDetailStateChannel.collect { channelState ->
            when (channelState) {
                is LatestNewsUiState.ChannelSuccess -> {
                    val channelStates = channelState.channelStates
                    if (channelState.channelStates.isNotEmpty()) {
                        binding.ivChannelProfile.load(channelStates.thumbnail.url) {
                            placeholder(R.drawable.img_profile_test)
                        }
                        binding.tvSubscribers.text = channelStates.subscribers.toString()
                    }
                }

                is LatestNewsUiState.Error -> initRVItem()
                else -> {}
            }
        }
    }
    private fun initViewModel() = lifecycleScope.launch { //변화 감지 갱신
        viewModel.uiDetailState.collect { uiDetailState ->
            when (uiDetailState) {
                is LatestNewsUiState.Success -> {
                    val videoStates = uiDetailState.videoStates //thumbnail
                    if (uiDetailState.videoStates.isNotEmpty()) {
                        binding.ivThumbnail.load(videoStates[0].thumbnail.url) {
                            placeholder(R.drawable.img_thumbnail_test)
                        }
                        binding.tvTitle.text = videoStates[0].title
                        binding.tvChannel.text = videoStates[0].channelTitle
                        binding.tvDescription.text = videoStates[0].description
                    }
                }

                is LatestNewsUiState.Error -> initRVItem()
                else -> {}
            }
        }
    }



    private fun initRVItem() = with(binding) {
    }

    private fun checkVideoIcon(videoId: String) = lifecycleScope.launch() {
        withContext(Dispatchers.IO) {
            val like = binding.ivLikesButton
            if (db.videoDao().isThisVideoExists(videoId)) {
                like.setImageResource(R.drawable.ic_likes)
            }
        }
    }
    private fun savedLikes(videoId: String) = lifecycleScope.launch() { //https://velog.io/@jeongminji4490/Error-cannot-access-database-on-main-thread-LifecycleScope
        withContext(Dispatchers.IO){
            val like = binding.ivLikesButton

            if (!db.videoDao().isThisVideoExists(videoId)) { //db에 해당  videoId를 가진 영상이 없는 경우. 추가.
                like.setImageResource(R.drawable.ic_likes)
//                db.videoDao().insertVideoEntityWithParameters(
//                    videoId = videoId,
//                    title = binding.tvTitle.text.toString(),
//                    description = binding.tvDescription.text.toString(),
//                    channelTitle =  binding.tvChannel.text.toString(),
//                    channelId = "",
//                    publishedAt = "",
//                    duration = "",
//                    thumbnailUrl = "")

                currentVideoState?.let {
                    db.videoDao().insertVideoEntityWithParameters(
                        videoId = it.id,
                        title = it.title,
                        description = it.description,
                        channelTitle =  it.channelTitle,
                        channelId = it.channelId,
                        publishedAt = it.publishedAt,
                        duration = it.duration,
                        thumbnailUrl = it.thumbnail.url)
                }

            }
            else { //db에 해당 videoId를 가진 영상이 있는 경우. 제외.
                like.setImageResource(R.drawable.ic_likes_outline)
                db.videoDao().deleteVideoEntityById(videoId = videoId)
            }
        }

    }


}