package com.example.mybmi_caculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow
import kotlin.math.round

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //main ac에서 입력한 값들을 끌고와서 집어넣어줌
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        //BMI 계산파트 : 체중 / 신장^2
        var value = weight / (height/100.0).pow(2.0)
        value = round(value*10)/10 //소수점 첫째자리까지 표혐

        var resText = ""
        var resImage = 0
        var resColor = 0

        if(value < 18.5) {
            resText = "저체중"
            resImage = R.drawable.img_lv1
            resColor = Color.YELLOW
        } else if(value >= 18.5 && value < 23.0) {
            resText = "정상체중"
            resImage = R.drawable.img_lv2
            resColor = Color.GREEN
        } else if(value >= 23.0 && value < 25.0) {
            resText = "과체중"
            resImage = R.drawable.img_lv3
            resColor = Color.BLACK
        } else if(value >= 25.0 && value < 30.0) {
            resText = "경도비만"
            resImage = R.drawable.img_lv4
            resColor = Color.CYAN
        } else if(value >= 30.0 && value < 35.0) {
            resText = "중정도비만"
            resImage = R.drawable.img_lv5
            resColor = Color.MAGENTA
        } else {
            resText = "고도비만"
            resImage = R.drawable.img_lv6
            resColor = Color.RED
        }

        val tv_resValue = findViewById<TextView>(R.id.tv_resValue)
        val tv_resText = findViewById<TextView>(R.id.tv_resText)
        val iv_image = findViewById<ImageView>(R.id.iv_image)
        val btn_back = findViewById<Button>(R.id.btn_back)

        tv_resValue.text = value.toString()
        tv_resText.text = resText
        iv_image.setImageResource(resImage)
        tv_resText.setTextColor(resColor)

        btn_back.setOnClickListener {
            finish()
        }

    }
}