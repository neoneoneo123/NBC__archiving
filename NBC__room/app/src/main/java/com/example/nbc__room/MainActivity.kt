package com.example.nbc__room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nbc__room.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var myDao: MyDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDao = MyDatabase.getDatabase(this).getMyDao() //데이터베이스를 받아옴

        val allStudents = myDao.getAllStudents() //모든 학생 데이터를 가져옴(라이브데이터)

        allStudents.observe(this) {
            val str = StringBuilder().apply {
                for ((id, name) in it) { //안에 들어있는 모든 학생 데이터를 순회하면서 붙임
                    append(id)
                    append("-")
                    append(name)
                    append("\n")
                }
            }.toString()
            binding.textStudentList.text = str
        }

        binding.addStudent.setOnClickListener {
            val id = binding.editStudentId.text.toString().toInt()
            val name = binding.editStudentName.text.toString()

            if (id > 0 && name.isNotEmpty()) { //id, name이 비어있는지 확인
                CoroutineScope(Dispatchers.IO).launch {
                    myDao.insertStudent(Student(id, name)) //데이터를 추가
                }
            }

            binding.editStudentId.text = null //삽입되고나면 칸을 비워줌
            binding.editStudentName.text = null
        }

        binding.queryStudent.setOnClickListener {
            val name = binding.editStudentName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {

                val results = myDao.getStudentByName(name) //이름으로 학생을 받아옴

                if (results.isNotEmpty()) {
                    val str = StringBuilder().apply {
                        results.forEach { student ->
                            append(student.id)
                            append("-")
                            append(student.name)
                        }
                    }
                    withContext(Dispatchers.Main) { //데이터베이스 결과에 따라서 도는 코루틴이 별도의 스레드에서 돌기 때문에 main을 걸어주어야 UI를 업데이트 할 수 있음
                        binding.textQueryStudent.text = str
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        binding.textQueryStudent.text = ""
                    }
                }
            }
        }
    }
}