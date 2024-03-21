package com.example.nbc__basictaskweek1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupButton()
        setRandomValueBetweenOneToHundred()
        setJobAndLaunch()
    }

    private fun setupButton() {
        // TODO("activity_main.xml에 작성되어 있는 button을 findViewById를 사용하여 button이라는 Button 타입의 변수에 할당하기)
        val button = findViewById<Button>(R.id.clickButton)

        // 위 코드가 작성되어야 아래 코드가 수행될 수 있음!
        button.setOnClickListener {
            job?.cancel()
            checkAnswerAndShowToast()
        }
    }

    private fun setRandomValueBetweenOneToHundred() {
        // TODO("activity_main.xml에 작성되어 있는 textViewRandom을 findViewById를 사용하여 randomTextView라는 TextView 타입의 변수에 할당하기)
        val randomTextView = findViewById<TextView>(R.id.textViewRandom)

        // TODO("Kotlin Random을 찾아보고 1이상 100이하의 숫자를 랜덤으로 구하여 Int 타입의 randomValue라는 변수에 할당하는 코드 작성하기")
        var randomValue = Random.nextInt(1, 100)

        // TODO("위 random으로 구한 값을 randomTextView에 세팅하여 화면에 보여주도록 하기")
        randomTextView.text = randomValue.toString()
    }

    private fun setJobAndLaunch() {
        // TODO("activity_main.xml에 작성되어 있는 spartaTextView를 findViewById를 사용하여 textView라는 TextView 타입의 변수에 할당하기)
        val textView = findViewById<TextView>(R.id.spartaTextView)

        /*
        job = lifecycleScope.launch {
            var i = 1
            while (isActive && i <= 100) {
                textView.text = i.toString()
                delay(500)
                i += 1 // ++i, i++
            }
        }
        */

        job = lifecycleScope.launch {
            // TODO("위 주석처리된 while 루프 코드를 참조하여 for 루프로 작성해보기(1이상 100이하)")
            for (i in 1 .. 100) {
                if (isActive) {
                    textView.text = i.toString()
                    delay(500)
                }
            }
        }
    }


    private fun checkAnswerAndShowToast() {
        // TODO("activity_main.xml에 작성되어 있는 spartaTextView를 findViewById를 사용하여 textView라는 TextView 타입의 변수에 할당하기)
        val textView = findViewById<TextView>(R.id.spartaTextView)

        // TODO("activity_main.xml에 작성되어 있는 textViewRandom을 findViewById를 사용하여 randomTextView라는 TextView 타입의 변수에 할당하기)
        val randomTextView = findViewById<TextView>(R.id.textViewRandom)

        // TODO("if문 사용해보기 - 위에서 2개의 변수를 작성완료하고 나면 textView의 값과 randomTextView의 값이 같은지 다른지를 확인하여 Toast 띄우기")
        /**
         * Toast 사용 예)
         * Toast.makeText(this, "메세지", Toast.LENGTH_SHORT).show() // Toast.LENGTH_SHORT 대신 Toast.LENGTH_LONG 또한 사용 가능
         */
        if (textView.text == randomTextView.text) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }
    }

}