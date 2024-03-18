package com.example.nbc__lab3_dice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.text_views)

//        val tv_num = findViewById<TextView>(R.id.tv_number)
//        val btn_dice = findViewById<Button>(R.id.btn_roll)
//
//        btn_dice.setOnClickListener {
//            val random = Random
//            val num = random.nextInt(6) + 1 //+1을 해야 1부터 시작함
//
//            tv_num.text = num.toString()
//
//            Log.d("MainActivity", "num = ${num.toString()}")
//        }
    }

    fun doAction(v: View) {
        Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show()
    }
}