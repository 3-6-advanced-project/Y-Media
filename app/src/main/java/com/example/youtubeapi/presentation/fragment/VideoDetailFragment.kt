package com.example.youtubeapi.presentation.fragment

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
import com.example.youtubeapi.data.local.dao.VideoEntityDao
import com.example.youtubeapi.data.model.entity.VideoEntity
import com.example.youtubeapi.databinding.FragmentVideoDetailBinding
import com.example.youtubeapi.viewmodel.LatestNewsUiState
import com.example.youtubeapi.viewmodel.MainViewModel
import com.example.youtubeapi.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VideoDetailFragment : Fragment() {

    private val binding by lazy { FragmentVideoDetailBinding.inflate(layoutInflater) }
    private val db by lazy { AppDatabase.getInstance(requireContext())!! }
    private val viewModel: MainViewModel by activityViewModels { //뷰모델 초기화 시 입력값 설정이 없어서 생긴 문제.
        MainViewModelFactory(db.videoDao())
    }
    private val videoId = "Sg95nokmww8" //나중에는 다른 fragment에서 보낸 정보를 여기 연결.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel() //데이터 바뀔때 자동 호출됨. coroutine

        //뒤로가기 버튼을 누르면 VideoDetailFragment가 사라지게 처리되고, 하단 탭 작동이 됨.
        binding.ivBackButton.setOnClickListener{
            binding.cl.visibility = android.view.View.GONE
        }

        binding.btVideo1Test.setOnClickListener {
            viewModel.onDetail(videoId)

            val like = binding.ivLikesButton
            if (db.videoDao().isThisVideoExists(videoId)) { //db에 해당  videoId를 가진 영상이 없는 경우. 추가.
                like.setImageResource(R.drawable.ic_likes)
                db.videoDao().insertVideoEntityWithParameters(
                videoId = videoId,
                title = "집중력과 인내심이 필요한 장난감 #shorts",
                description = "",
                channelTitle =  "신사장",
                channelId = "UCdyeqVNyJehPmKBEJ520rhg",
                publishedAt = "2022-03-13T02:12:08Z",
                duration = "PT23M24S",
                thumbnailUrl = "https://i.ytimg.com/vi/Sg95nokmww8/hqdefault.jpg")
            }
            else { //db에 해당 videoId를 가진 영상이 있는 경우. 제외.
                like.setImageResource(R.drawable.ic_likes_outline)
                db.videoDao().deleteVideoEntityById(videoId = videoId)
            }
        }
    }

    private fun initViewModel() = lifecycleScope.launch { //변화 감지 갱신
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is LatestNewsUiState.Success -> {
                    val videoStates = uiState.videoStates //thumbnail

                    if(videoStates.isNotEmpty()) { //viewModel 완전 초기값... 비어있어 인덱스 오류?
                        binding.ivThumbnail.load(videoStates[0].thumbnail.url){ //glide나 [coil]로 웹 이미지 로드
                            placeholder(R.drawable.img_thumbnail_test) //적용 전
                        }
                        Log.d("videoStates", videoStates.size.toString())
                        binding.tvTitle.text = videoStates[0].title
                        binding.tvChannel.text = videoStates[0].channelTitle
                        binding.ivChannelProfile.load(videoStates[0].thumbnail.url) //우선 섬네일 넣어뒀는데 채널 사진으로 바꾸어야 됨. 갖고 올 수 있는 건가???
                        binding.tvSubscribers.text = "?"
                        binding.tvDescription.text = videoStates[0].description
                    }
                }
                is LatestNewsUiState.Error -> initRVItem()
            }
        }
    }

    private fun initRVItem() = with(binding) {
    }


}

