package com.example.mybmi_caculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText = findViewById<EditText>(R.id.et_height)
        val weightEditText = findViewById<EditText>(R.id.et_weight)
        val submitButton = findViewById<Button>(R.id.btn_check)

        //버튼을 눌렀을 때 데이터를 넘겨줘야 함. 버튼을 클릭하면 이 아래 {}가 실행됨
        submitButton.setOnClickListener {

            //아무 값도 넣지 않고 버튼을 눌렀을 때에 대한 예외처리가 필요함
            if(heightEditText.text.isEmpty()) {
                Toast.makeText(this, "신장을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(weightEditText.text.isEmpty()) {
                Toast.makeText(this, "체중을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //height라는 변수를 선언해놓고 editText에서 받아온 String형태의 text를 Int형으로 변환하겠다.
            val height : Int = heightEditText.text.toString().toInt()
            val weight : Int = weightEditText.text.toString().toInt()

            //ac에서 ac로 데이터를 넘길 때 intent를 씀. 화면 넘기는 파트
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            startActivity(intent)

        }
    }
}