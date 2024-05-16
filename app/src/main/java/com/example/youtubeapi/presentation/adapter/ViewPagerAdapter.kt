package com.example.youtubeapi.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.youtubeapi.presentation.fragment.HomeFragment
import com.example.youtubeapi.presentation.fragment.SearchFragment
import com.example.youtubeapi.presentation.fragment.MyVideoFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> MyVideoFragment()
            else -> { throw Exception() }
        }
    }
}