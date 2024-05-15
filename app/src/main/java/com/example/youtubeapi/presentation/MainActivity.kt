package com.example.youtubeapi.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.youtubeapi.HomeFragment_TAG
import com.example.youtubeapi.MyVideoFragment_TAG
import com.example.youtubeapi.R
import com.example.youtubeapi.SearchFragment_TAG
import com.example.youtubeapi.adapter.ViewPagerAdapter
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        clickedView()
    }

    private fun initView() {
        with(binding) {
            vp.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tl, vp) { tab, position ->
                when(position) {
                    0 -> {
                        tab.setIcon(R.drawable.ic_home) //초기 화면. 그런데 이렇게 코드를 짰을 때 문제는 다른 탭/뷰페이저가 지정된 상태에서 앱이 시작했을 때다.
                        tab.text = HomeFragment_TAG
                    }
                    1 -> {
                        tab.setIcon(R.drawable.ic_search_outline)
                        tab.text = SearchFragment_TAG
                    }
                    2 -> {
                        tab.setIcon(R.drawable.ic_likes_outline)
                        tab.text = MyVideoFragment_TAG
                    }
                }
            }.attach()
        }
    }

    private fun clickedView() { //탭 선택시
        binding.tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val white = ColorStateList.valueOf(ContextCompat.getColor(applicationContext, R.color.white))
                when(tab?.position){
                    0 -> {
                        tab.setIcon(R.drawable.ic_home)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.ic_search)
                    }
                    2 -> {
                        tab.setIcon(R.drawable.ic_likes)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        tab.setIcon(R.drawable.ic_home_outline)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.ic_search_outline)
                    }
                    2 -> {
                        tab.setIcon(R.drawable.ic_likes_outline)
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }
}