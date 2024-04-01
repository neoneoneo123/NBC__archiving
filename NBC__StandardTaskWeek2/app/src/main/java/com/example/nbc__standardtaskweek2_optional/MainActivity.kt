package com.example.nbc__standardtaskweek2_optional

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nbc__standardtaskweek2_optional.databinding.DonutDetailFragmentBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        var btn1 = findViewById<TextView>(R.id.tv_title_button)

        btn1.setOnClickListener {
            setFrag()
        }
    }

    private fun setFrag() {
        val fm = supportFragmentManager.beginTransaction() //프래그먼트 생성 및 트랜잭션 시작
        fm.replace(R.id.fl_fm_list, DonutListFragment()).commit() //트랜잭션을 통해 프래그먼트 커밋
    }

    private fun setFragDetail() {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.cl_fm_detail, DonutDetailFragment()).addToBackStack("detail").commit() //뒤로가기 버튼을 사용하기 위한 처리를 함께 해줌
    }
}

