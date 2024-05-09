package com.example.nbc_standard_week7.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc_standard_week7.R
import com.example.nbc_standard_week7.databinding.ActivityMainBinding
import com.example.nbc_standard_week7.presentation.detail.DetailAcitivity
import com.example.nbc_standard_week7.presentation.model.FoodItemModel
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModel
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: FoodItemViewModel by viewModels<FoodItemViewModel> {
        FoodItemViewModelFactory.newInstance()
    }

    private val adapter: FoodItemAdapter by lazy {
        FoodItemAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        searchItems()
        makeView()
    }

    private fun searchItems() {
        binding.btnSearch.setOnClickListener {
            val targetText = binding.tvEdit.text.toString()
            viewModel.getB553748List(1, 10, targetText)
        }
    }

    private fun makeView() {
        viewModel.getB553748SearchResult.observe(this) {
            adapter.setViewItems(it)
        }

        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : FoodItemAdapter.ItemClick {
            override fun onClick(view: View, item: FoodItemModel) {
                selectImage(item)
            }
        }
    }

    private fun selectImage(item: FoodItemModel) {
        val intent = Intent(this, DetailAcitivity::class.java)
        val bundle = Bundle().apply {
            putParcelable("SEND", item)
        }

        intent.putExtras(bundle)
        startActivity(intent)
    }
}