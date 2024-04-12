package com.example.nbc__neomarket

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nbc__neomarket.databinding.ActivityDetailBinding
import com.example.nbc__neomarket.data.Item
import com.example.nbc__neomarket.data.ItemDataSource
import com.example.nbc__neomarket.data.User
import com.example.nbc__neomarket.data.UserDataSource
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //상품 데이터 생성
        val item : Item? by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("item", Item::class.java)
            } else {
                intent.getParcelableExtra<Item>("item")
            }
        }

        //사용자 데이터 생성
        val userData = UserDataSource.getUserDataSource().getUserList()
        val thisUser = userData.find { it.userId == item!!.seller }

        //화면에 그리기
        initialize(item, thisUser)

        //좋아요 처리
        doLike(item)

        //백버튼
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    //좋아요
    private fun doLike(item: Item?) {
        binding.ivHeartDetail.setOnClickListener {
            //아이콘 변경 및 데이터 변경
            if (item!!.isLike) {
                ItemDataSource.getDataSource().downLike(item.id)
                binding.ivHeartDetail.setImageResource(R.drawable.ic_heart_empty)
            } else {
                ItemDataSource.getDataSource().upLike(item.id)
                binding.ivHeartDetail.setImageResource(R.drawable.ic_heart_full)
            }

            //메시지 표시



        }
    }

    private fun initialize(item: Item?, thisUser: User?) {
        binding.ivItemDetail.setImageResource(item!!.image)
        binding.ivSellerDetail.setImageResource(R.drawable.ic_user)
        binding.tvNameDetail.text = item.seller
        binding.tvAdressDetail.text = item.address
        binding.tvTitleDetail.text = item.title
        binding.tvDescriptionDetail.text = item.description
        binding.tvPriceDetail.text = DecimalFormat("#,###원").format(item.price)
        binding.tvMannerDetail.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.tvTemperatureDetail.text = thisUser!!.userTemperature.toString()
        var temperatureFace = ""
        var temperatureColor = 0
        when (thisUser.userTemperature) {
            in 0.0 .. 12.5 -> {
                temperatureFace = "😠"
                temperatureColor = R.color.manner_gray
            }
            in 12.6 .. 30.0 -> {
                temperatureFace = "🙁"
                temperatureColor = R.color.manner_blue
            }
            in 30.1 .. 36.5 -> {
                temperatureFace = "🙂"
                temperatureColor = R.color.manner_sky
            }
            in 36.6 .. 50.5 -> {
                temperatureFace = "😃"
                temperatureColor = R.color.manner_green
            }
            in 50.6 .. 65.5 -> {
                temperatureFace = "😄"
                temperatureColor = R.color.manner_yellow
            }
            in 65.6 .. 88.0 -> {
                temperatureFace = "😆"
                temperatureColor = R.color.manner_orange
            }
            in 88.1 .. 99.9 -> {
                temperatureFace = "😇"
                temperatureColor = R.color.manner_orange
            }
        }
        binding.tvFaceDetail.text = temperatureFace

        binding.tvTemperatureDetail.setTextColor(this.getColor(temperatureColor))

        if (item.isLike) {
            binding.ivHeartDetail.setImageResource(R.drawable.ic_heart_full)
        } else {
            binding.ivHeartDetail.setImageResource(R.drawable.ic_heart_empty)
        }
    }
}