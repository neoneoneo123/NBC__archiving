package com.example.nbc__dust

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nbc__dust.data.DustItem
import com.example.nbc__dust.databinding.ActivityMainBinding
import com.example.nbc__dust.retrofit.NetWorkClient
import com.skydoves.powerspinner.IconSpinnerAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var items = mutableListOf<DustItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("miseya", "onCreate")

        //도시 선택
        //스피너에서 항목을 선택했을 때 리스너
        binding.spinnerViewSido.setOnSpinnerItemSelectedListener<String> { _, _, _, text ->
            Log.d("miseya", "seletedItem: spinnerViewSido selected >  $text")
            communicateNetWork(setUpDustParameter(text))
        }

        //지역 선택
        binding.spinnerViewGoo.setOnSpinnerItemSelectedListener <String> { _, _, _, text ->
            Log.d("miseya", "seletedItem: spinnerViewGoo selected >  $text")
            val selectedItem = items.filter { f -> f.stationName == text }

            Log.d("miseya", "seletedItem: sidoName > " + selectedItem[0].sidoName)
            Log.d("miseya", "seletedItem: pm10Value > " + selectedItem[0].pm10Value)

            binding.tvCityname.text = selectedItem[0].sidoName + " " + selectedItem[0].stationName
            binding.tvDate.text = selectedItem[0].dataTime
            binding.tvP10value.text = selectedItem[0].pm10Value + " ㎍/㎥"

            when (getGrade(selectedItem[0].pm10Value)) {
                1 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#9ED2EC"))
                    binding.ivFace.setImageResource(R.drawable.mise1)
                    binding.tvP10grade.text = "좋음"
                }

                2 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#D6A478"))
                    binding.ivFace.setImageResource(R.drawable.mise2)
                    binding.tvP10grade.text = "보통"
                }

                3 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#DF7766"))
                    binding.ivFace.setImageResource(R.drawable.mise3)
                    binding.tvP10grade.text = "나쁨"
                }

                4 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#BB3320"))
                    binding.ivFace.setImageResource(R.drawable.mise4)
                    binding.tvP10grade.text = "매우나쁨"
                }
            }
        }
    }

    /**
     * 요청 파라미터를 받아와서 처리
     * DB 연결, Http 통신은 메인 스레드에서 할수 없으므로 코루틴을 사용함
     * 이 부분은 별도의 스레드에서 처리하는 것임
     */
    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {

        val responseData = NetWorkClient.dustNetWork.getDust(param) //인터페이스의 getDust 호출
        Log.d("Parsing Dust ::", responseData.toString())

//        val adapter = IconSpinnerAdapter(binding.spinnerViewSido)

        items = responseData.response.dustBody.dustItem!!

        //스피너에 넣어줄 goo 데이터를 취득
        val goo = ArrayList<String>()
        items.forEach {
            Log.d("add Item :", it.stationName)
            goo.add(it.stationName)
        }

        //스피너에 데이터를 넣어줌
        runOnUiThread { //UI스레드에서 처리되도록 함
            binding.spinnerViewGoo.setItems(goo)
        }
    }

    /**
     * 요청 파라미터 생성 함수
     */
    private fun setUpDustParameter(sido: String): HashMap<String, String> {
        val authKey = "cpaEff+fBSNn0sePBS09fjpwKSP2XnJekX3fFbhMcL5FCB7ATlANCCM/VWa3k+Jw5N4NMgFSNhVIcITL7HA5+w=="

        //데이터 제공자에서 제시하는 요청 변수명으로 해야함
        return hashMapOf(
            "serviceKey" to authKey,
            "returnType" to "json",
            "numOfRows" to "100",
            "pageNo" to "1",
            "sidoName" to sido,
            "ver" to "1.0"
        )
    }

    fun getGrade(value: String): Int {
        val mValue = value.toInt()
        var grade = 1
        grade = if (mValue >= 0 && mValue <= 30) 1
        else if (mValue >= 31 && mValue <= 80) 2
        else if (mValue >= 81 && mValue <= 100) 2
        else 4

        return grade
    }
}