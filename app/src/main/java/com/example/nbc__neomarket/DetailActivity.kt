package com.example.nbc__neomarket

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.nbc__neomarket.databinding.ActivityDetailBinding
import com.example.nbc__neomarket.data.Item
import com.example.nbc__neomarket.data.ItemDataSource
import com.example.nbc__neomarket.data.User
import com.example.nbc__neomarket.data.UserDataSource
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var item: Item? = null
    private var user: User? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //데이터 생성
        item = intent.getParcelableExtra("item")
        val userData = UserDataSource.getUserDataSource().getUserList()
        user = userData.find { it.userId == item!!.seller }

        //화면에 그리기
        initialize()

        //좋아요 처리
        binding.ivHeartDetail.setOnClickListener {
            toggleLike()
        }

        //백버튼
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    //화면 요소에 데이터 적용
    private fun initialize() {
        binding.ivItemDetail.setImageResource(item!!.image)
        binding.ivSellerDetail.setImageResource(R.drawable.ic_user)
        binding.tvNameDetail.text = item!!.seller
        binding.tvAdressDetail.text = item!!.address
        binding.tvTitleDetail.text = item!!.title
        binding.tvDescriptionDetail.text = item!!.description
        binding.tvPriceDetail.text = DecimalFormat("#,###원").format(item!!.price)
        binding.tvMannerDetail.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.tvTemperatureDetail.text = user!!.userTemperature.toString()
        var temperatureFace = ""
        var temperatureColor = 0
        when (user!!.userTemperature) {
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

        updateLike()
    }

    //좋아요 아이콘 변경
    private fun updateLike() {
        if (item!!.isLike) {
            binding.ivHeartDetail.setImageResource(R.drawable.ic_heart_full)
        } else {
            binding.ivHeartDetail.setImageResource(R.drawable.ic_heart_empty)
        }
    }

    //좋아요 데이터 변경
    private fun toggleLike() {
        if (item!!.isLike) {
            Log.d("여기는 디테일", item!!.isLike.toString())
            ItemDataSource.getDataSource().downLike(item!!.id)
        } else {
            Log.d("여기는 디테일", item!!.isLike.toString())
            ItemDataSource.getDataSource().upLike(item!!.id)
            Snackbar.make(binding.root, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
        }
        item!!.isLike = !item!!.isLike
        updateLike()
    }
}