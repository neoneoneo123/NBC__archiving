package com.example.nbc__neomarket

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc__neomarket.data.Item
import com.example.nbc__neomarket.databinding.ActivityMainBinding
import com.example.nbc__neomarket.data.ItemDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val data = ItemDataSource.getDataSource().getItemList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //스크롤 버튼
        binding.rvList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstPosition = layoutManager.findFirstVisibleItemPosition()

                if (firstPosition > 0) {
                    binding.fbScroll.show()
                } else {
                    binding.fbScroll.hide()
                }
            }
        })
        binding.fbScroll.setOnClickListener {
            binding.rvList.smoothScrollToPosition(0)
        }


        //알림 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 99)
        }

        //알림 생성
        binding.ivBell.setOnClickListener {
            makeNotification()
        }
    }

    override fun onResume() {
        super.onResume()
        setRecyclerView(data)
    }

    private fun setRecyclerView(data: List<Item>) {
        val adapter = ItemAdapter(data)
        val decoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        binding.rvList.addItemDecoration(decoration)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)

        //아이템 클릭하여 디테일 페이지 이동
        adapter.itemClick = object : ItemAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("item", data[position])
                startActivity(intent)
            }
        }

        //아이템 롱클릭
        adapter.itemLongClick = object  : ItemAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                itemDeleteCheck(position)
            }
        }
    }

    //아이템 삭제 다이얼로그
    private fun itemDeleteCheck(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("상품 삭제")
        builder.setMessage("상품을 정말로 삭제하시겠습니까?")
        builder.setIcon(R.drawable.ic_chat)

        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
            val thisId = data[position].id
            ItemDataSource.getDataSource().removeItem(thisId)
            binding.rvList.adapter?.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, "상품이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }

        builder.show()
    }

    //알림
    private fun makeNotification() {
        //채널 정의
        val CHANNEL_ID = "neo_channel_id"
        val CHANNEL_NAME = "Neo Channel"

        //notification 정의
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("키워드 알림")
            .setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")

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