package com.example.youtubeapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeapi.HomeFragment_TAG
import com.example.youtubeapi.MyVideoFragment_TAG
import com.example.youtubeapi.R
import com.example.youtubeapi.SearchFragment_TAG
import com.example.youtubeapi.adapter.ViewPagerAdapter
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        with(binding) {
            vp.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tl, vp) { tab, position ->
                when(position) {
                    0 -> {
                        tab.setIcon(R.drawable.ic_home_outline)
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
}