package com.example.nbc__neomarket

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc__neomarket.databinding.ActivityMainBinding
import com.example.nbc__neomarket.itemData.DataSource
import com.google.android.material.snackbar.Snackbar

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


        //알림 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 99)
        }

        //알림 생성
        binding.ivBell.setOnClickListener {
            makeNotification()
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == 99) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //Toast.makeText(this, "승인", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                //Toast.makeText(this, "승인 안됨", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun makeNotification() {
        //채널 정의
        val CHANNEL_ID = "neo_channel_id"
        val CHANNEL_NAME = "Neo Channel"

        //notification 정의
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("제목")
            .setContentText("내용내용")

        //notification 생성
        val notificationManger =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //SDK 26 이상이므로 생성할 알람에 채널 지정 / 알람 권한 요청하기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManger.createNotificationChannel(channel)
        }

        //알람 띄우기
        notificationManger.notify(0, notificationBuilder.build())
    }


    //back 버튼 다이얼로그
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("키워드 알림")
        builder.setMessage("설정한 키워드에 대한 알림이 도착했습니다.")
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