package com.example.nbc__standardtaskweek1

import android.content.Intent
import android.os.Bundle
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        window.insetsController?.hide(WindowInsets.Type.statusBars())
        val btnSignIn = findViewById<FrameLayout>(R.id.fl_btn_sign_in)
        btnSignIn.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }
    }
}