package com.example.nbc__appstarttask

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val strIdData = intent.getStringExtra("dataFromSignInActivity_id")
        val textId = findViewById<TextView>(R.id.tv_data)
        textId.text = strIdData

        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
    }
}