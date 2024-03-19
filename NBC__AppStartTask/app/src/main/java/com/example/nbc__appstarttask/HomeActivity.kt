package com.example.nbc__appstarttask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.nbc__appstarttask.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private var homeBinding: ActivityHomeBinding? = null
    private val binding get() = homeBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        //송신 데이터 출력
        val strIdData = intent.getStringExtra("dataFromSignInActivity_id")
        val textId = findViewById<TextView>(R.id.tv_id_data)
        textId.text = "아이디 : " + strIdData

        //로그인 성공 메세지
        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

        //웹 뷰
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()

        //블로그 공개 여부 스위치
        val webViewBlog = findViewById<WebView>(R.id.webView)
        val switchBlog = findViewById<Switch>(R.id.sw_blog)
        var swichBlogBoolSame = true

        switchBlog.setOnCheckedChangeListener { buttonView, isChecked ->
            var swiichBlogBool = switchBlog.isChecked
            if (swiichBlogBool == true) {
                Log.d("HomeActivity", "isChecked")
                webViewBlog.visibility = View.VISIBLE
                binding.webView.loadUrl("https://neoneoneocat.tistory.com")
            } else {
                Log.d("HomeActivity", "isChecked false")
                webViewBlog.visibility = View.GONE
                webViewBlog.stopLoading()
            }
            swichBlogBoolSame = swiichBlogBool
        }

        //개인 블로그 정보 공개 동의 체크 박스
        val checkBlog = findViewById<CheckBox>(R.id.cb_blog)
        var checkBlogBoolSame = true
        checkBlog.setOnCheckedChangeListener { buttonView, isChecked ->
            var checkBlogBool = checkBlog.isChecked
            if (checkBlogBool == true) {
                checkBlogBoolSame = true
                Log.d("HomeActivity", "체크박스 true")

            }
            else {
                Log.d("HomeActivity", "체크박스 false")
                checkBlogBoolSame = false
            }
        }

        //종료 버튼
        val btn_close = findViewById<Button>(R.id.btn_close)
        btn_close.setOnClickListener {

            if (swichBlogBoolSame && checkBlogBoolSame) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "정보 공개 동의를 해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

