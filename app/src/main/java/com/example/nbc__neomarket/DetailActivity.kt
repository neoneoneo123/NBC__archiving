package com.example.nbc__neomarket

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nbc__neomarket.databinding.ActivityDetailBinding
import com.example.nbc__neomarket.databinding.ActivityMainBinding
import com.example.nbc__neomarket.itemData.Item

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item : Item? by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("item", Item::class.java)
            } else {
                intent.getParcelableExtra<Item>("item")
            }
        }

        val title = intent.getStringExtra("title")
        Log.d("여기는 디테일액티비티", item.toString())
        binding.tvTemp.text = title


    }
}