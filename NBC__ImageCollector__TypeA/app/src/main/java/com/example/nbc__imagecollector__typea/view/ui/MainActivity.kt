package com.example.nbc__imagecollector__typea.view.ui
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.nbc__imagecollector__typea.R
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.databinding.ActivityMainBinding
import com.example.nbc__imagecollector__typea.repository.SearchRepository
import com.example.nbc__imagecollector__typea.repository.SearchRepositoryImpl
import com.example.nbc__imagecollector__typea.service.NetWorkClient
import com.example.nbc__imagecollector__typea.view.adapter.ViewPagerAdapter
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModel
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        //ViewPager2 adapter
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(ImageSearchFragment())
        viewPagerAdapter.addFragment(MyBoxFragment())

        //adapter 연결
        binding.viewPager.apply {
            adapter = viewPagerAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = (R.string.image_search).toString()
                1 -> tab.text = (R.string.my_box).toString()
            }
        }.attach()
    }
}