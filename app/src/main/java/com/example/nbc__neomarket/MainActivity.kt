package com.example.nbc__neomarket

import android.content.DialogInterface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc__neomarket.databinding.ActivityMainBinding
import com.example.nbc__neomarket.itemData.DataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerView
        val data = DataSource.getDataSource().getItemList()
        val adapter = ItemAdapter(data)
        val decoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        binding.rvList.addItemDecoration(decoration)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)

    }

    //back 버튼 다이얼로그
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.ic_chat)

        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
            super.onBackPressed()
            finish()
        }
        builder.setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }

        builder.show()
    }
}