package com.example.nbc__mymbti

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager2

    val questionaireResults = QuestionaireResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false //화면을 움직이면 새로운 화면이 나오지 않도록 함. 응답을 받은 다음에 넘거야 함
    }

    fun moveToNextQuestion() {
        if(viewPager.currentItem == 3) { //마지막 페이지면
            //결과 화면으로 이동 시켜야함
            var intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results", ArrayList(questionaireResults.results))
            startActivity(intent)

        } else {
            val nextItem = viewPager.currentItem + 1
            if(nextItem < viewPager.adapter?.itemCount ?: 0) {
                viewPager.setCurrentItem(nextItem, true) //다음 페이지 번호를 주면 넘기도록
            }
        }
    }
}

class QuestionaireResults { //질문지별 응답 저장해둘 곳
    val results = mutableListOf<Int>()

    fun addResponses(response: List<Int>) { //응답을 리스트로 저장해둠. 1이 많으면 리절트에 1을 저장
        val mostFrequent = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        mostFrequent?.let { results.add(it) }
    }
}