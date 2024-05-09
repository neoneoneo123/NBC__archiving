package com.example.nbc_standard_week7.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.nbc_standard_week7.R
import com.example.nbc_standard_week7.databinding.ActivityDetailAcitivityBinding
import com.example.nbc_standard_week7.databinding.ActivityMainBinding
import com.example.nbc_standard_week7.presentation.model.FoodItemModel
import com.example.nbc_standard_week7.presentation.util.UtilityUrlConverter
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModel
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModelFactory

class DetailAcitivity : AppCompatActivity() {

    private val binding: ActivityDetailAcitivityBinding by lazy {
        ActivityDetailAcitivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val item = intent.getParcelableExtra<FoodItemModel>("SEND")
        Log.d("detail activity", item.toString())

        binding.tvDetailName.text = item!!.name
        binding.tvDetailNu.text = item.nutrient

        val url = UtilityUrlConverter.fromString(item.image)
        Glide.with(this).load(url).into(binding.ivDetailImage)
    }
}