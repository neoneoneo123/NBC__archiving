package com.example.nbc__imagecollector__typea.view.ui
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.nbc__imagecollector__typea.R
import com.example.nbc__imagecollector__typea.databinding.ActivityMainBinding
import com.example.nbc__imagecollector__typea.view.adapter.ViewPagerAdapter
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModel
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModelFactory
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



    override fun onResume() {
        super.onResume()
        Log.d("shared", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("shared", "onRestart")

    }

    override fun onPause() {
        super.onPause()
        Log.d("shared", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("shared", "onStop")

    }
}
