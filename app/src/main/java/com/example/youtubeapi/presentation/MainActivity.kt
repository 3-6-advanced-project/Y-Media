package com.example.youtubeapi.presentation

import android.content.res.ColorStateList
import android.os.Bundle
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
                        tab.icon?.clearColorFilter()
                    }
                    2 -> {
                        tab.setIcon(R.drawable.ic_likes_outline)
                        tab.text = MyVideoFragment_TAG
                    }
                }
            }.attach()
        }
    }

    private fun clickedView() { //선택된 탭.
        //코드가 왠지 불필요하게 길어졌다. 다음부턴 bottom navigation을 공부해야겠다.

        val white = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
        val grey = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.whitish_grey))
        /* 여기서 context 왜 지정해야 하는지 아시는 분?
        이전 getColor가 deprecated된 건 읽었는데, 컬러 코드 하나 가져오는데 왜 context까지 필요한지 이해가 안돼요 */

        binding.tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
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