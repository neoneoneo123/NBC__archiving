package com.example.nbc_standard_week7.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nbc_standard_week7.databinding.ActivityMainBinding
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(this)
    }

    private val viewModel: FoodItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        vpMain.adapter = viewPagerAdapter
        vpMain.offscreenPageLimit = viewPagerAdapter.itemCount

        TabLayoutMediator(tlTap, vpMain) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }
}