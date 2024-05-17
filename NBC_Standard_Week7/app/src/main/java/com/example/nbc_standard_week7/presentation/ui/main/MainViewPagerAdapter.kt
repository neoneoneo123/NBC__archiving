package com.example.nbc_standard_week7.presentation.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nbc_standard_week7.presentation.ui.favorite.FavoriteFragment
import com.example.nbc_standard_week7.presentation.ui.list.ListFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        MainTabModel(ListFragment.newInstance(), "list"),
        MainTabModel(FavoriteFragment.newInstance(), "favorite"),
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position].fragment

    fun getTitle(position: Int): String = fragments[position].title
}