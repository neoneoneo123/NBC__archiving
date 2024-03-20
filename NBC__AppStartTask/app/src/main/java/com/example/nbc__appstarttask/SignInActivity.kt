package com.example.nbc__appstarttask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    //데이터 수신용 콜백 등록
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val editTextId = findViewById<EditText>(R.id.et_id)
        val editTextPw = findViewById<EditText>(R.id.et_pw)

        //로그인 버튼
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
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
                //HomeActivity -> 데이터 전달
                val strIdData = editTextId.text.toString()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("dataFromSignInActivity_id", strIdData)

                startActivity(intent)
            }
        }

        //-> SignUpActivity 데이터 수신
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val id = result.data?.getStringExtra("id") ?: "??"
                val pw = result.data?.getStringExtra("pw") ?: "????"
                editTextId.setText(id)
                editTextPw.setText(pw)
            }
        }

        //회원가입 버튼
        val btnSignUp = findViewById<Button>(R.id.btn_signUp)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}