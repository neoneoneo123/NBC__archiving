package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.nbc__imagecollector__typea.R
import com.example.nbc__imagecollector__typea.databinding.ActivityMainBinding
import com.example.nbc__imagecollector__typea.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewPager()
    }

    /**
     * ViewPager, TabLayout을 그리는 함수입니다.
     */
    private fun initViewPager() {
        //ViewPager2 adapter
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(ImageSearchFragment())
        viewPagerAdapter.addFragment(MyBoxFragment())

        binding.viewPager.apply {
            adapter = viewPagerAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.image_search)
                1 -> tab.text = getString(R.string.my_box)
            }
        }.attach()
    }
}
