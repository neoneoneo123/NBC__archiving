package com.example.nbc__mylotto

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val clearButton by lazy { findViewById<Button>(R.id.btn_clear) }
    private val addButton by lazy { findViewById<Button>(R.id.btn_add) }
    private val runButton by lazy { findViewById<Button>(R.id.btn_run) }
    private val numPick by lazy { findViewById<NumberPicker>(R.id.np_num) }

    private val numTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.tv_num1),
            findViewById(R.id.tv_num2),
            findViewById(R.id.tv_num3),
            findViewById(R.id.tv_num4),
            findViewById(R.id.tv_num5),
            findViewById(R.id.tv_num6)
        )
    }

    private var didRun = false // run이 실행 중인지 체크
    private var pickNumberSet = hashSetOf<Int>()//지정한 숫자를 잠깐 담아둘 공간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //화면 위에 이 부분을 올림

        numPick.minValue = 1
        numPick.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            when {
                didRun -> showToast("초기화 후 시도해주세요.") //꽉 차있느냐?
                pickNumberSet.size >= 5 -> showToast("숫자는 최대 5개까지 선택할 수 있습니다.")
                pickNumberSet.contains(numPick.value) -> showToast("이미 선택된 숫자입니다.")
                else -> {
                    val textView = numTextViewList[pickNumberSet.size]
                    textView.isVisible = true
                    textView.text = numPick.value.toString()

                    setNumberBack(numPick.value, textView)
                    pickNumberSet.add(numPick.value)
                }
            }
        }
    }

    private fun initClearButton() { //모든 공 날리기
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numTextViewList.forEach { it.isVisible = false }
            didRun = false
            numPick.value = 1
        }
    }

    private fun initRunButton() { //자동 생성해줘야함.
        runButton.setOnClickListener {
            val list = getRandom()

            didRun = true

            list.forEachIndexed { index, number ->
                val textView = numTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true
                setNumberBack(number, textView)
            }
        }
    }

    private fun getRandom(): List<Int> {
        val numbers = (1 .. 45).filter { it !in pickNumberSet } //1~45 사이의 숫자 중 먼저 선택된 애를 뺀 나머지가 랜덤으로 들어감
        return (pickNumberSet + numbers.shuffled().take(6 - pickNumberSet.size)).sorted()
        //     기존 숫자에 더해          숫자를 섞어서       먼저 빠진 숫자 개수 만큼 빼고 취함
    }

    private fun setNumberBack(number: Int, textView: TextView) { //number가 1~9 사이면 노란색으로 처리 등
        val background = when(number) {
            in 1 .. 10 -> R.drawable.circle_yellow
            in 11 .. 20 -> R.drawable.circle_blue
            in 21 .. 30 -> R.drawable.circle_red
            in 31 .. 40 -> R.drawable.circle_gray
            else -> R.drawable.circle_green
        }
        textView.background = ContextCompat.getDrawable(this, background)

    }

    private fun showToast(messege: String) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show()
    }

}