package com.example.nbc__neomarket

import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Build.VERSION
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc__neomarket.databinding.ActivityMainBinding
import com.example.nbc__neomarket.itemData.DataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerView
        val data = DataSource.getDataSource().getItemList()
        val adapter = ItemAdapter(data)
        val decoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        binding.rvList.addItemDecoration(decoration)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)

    }
}