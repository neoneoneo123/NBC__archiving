package com.example.nbc__appstarttask

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        //회원가입 버튼
        val btnSignIn = findViewById<Button>(R.id.btn_signIn)
        btnSignIn.setOnClickListener {

            val editTextName = findViewById<EditText>(R.id.et_sign_name)
            val editTextId = findViewById<EditText>(R.id.et_sign_id)
            val editTextPw = findViewById<EditText>(R.id.et_sign_pw)

            if (editTextName.text.isNotBlank() && editTextId.text.isNotBlank() && editTextPw.text.isNotBlank()) {
                val intent = Intent(this, SignInActivity::class.java)

                intent.putExtra("id", editTextName.text.toString())
                intent.putExtra("pw", editTextPw.text.toString())
                setResult(RESULT_OK, intent)

                finish()
            }
            else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}