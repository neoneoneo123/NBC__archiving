package com.example.nbc__appstarttask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)




        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val editTextId = findViewById<EditText>(R.id.et_id)
            val editTextPw = findViewById<EditText>(R.id.et_pw)

            if (editTextId.text.isEmpty() && editTextPw.text.isEmpty()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if (editTextId.text.isEmpty()) {
                Toast.makeText(this, "아이디를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if (editTextPw.text.isEmpty()) {
                Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if (editTextId.text.isNotEmpty() && editTextPw.text.isNotEmpty()) {
                val strIdData = editTextId.text.toString()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("dataFromSignInActivity_id", strIdData)
                startActivity(intent)
            }
        }

        val btnSignUp = findViewById<Button>(R.id.btn_signUp)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}