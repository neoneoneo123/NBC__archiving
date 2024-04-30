package com.example.nbc__sharedpreferences

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nbc__sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveData()
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        loadData()
    }

    private fun saveData() {
        val preferences = getSharedPreferences("pref", 0)
        val edit = preferences.edit()

        edit.putString("name", binding.editText.text.toString())
        edit.apply()
    }

    private fun loadData() {
        val preferences = getSharedPreferences("pref", 0)

        binding.editText.setText(preferences.getString("name", ""))
    }
}