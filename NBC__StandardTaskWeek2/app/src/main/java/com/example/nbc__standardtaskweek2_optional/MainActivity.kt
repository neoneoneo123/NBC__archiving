package com.example.nbc__standardtaskweek2_optional

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc__standardtaskweek2_optional.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val menus = listOf(
        Menu("Little chicken", "R$ 9,99", R.drawable.img_donut1),
        Menu("Strawberry donut", "R$ 7,99", R.drawable.img_donut2),
        Menu("Almond donut", "R$ 8,99", R.drawable.img_donut3),
        Menu("Chocolate donut", "R$ 6,99", R.drawable.img_donut4),
        Menu("White donut", "R$ 9,00", R.drawable.img_donut5),
        Menu("Glazed donut", "R$ 7,00", R.drawable.img_donut6),
        Menu("neo donut", "R$ 7,77", R.drawable.img_donut7),
        Menu("donut?", "R$ 4,44", R.drawable.img_donut8),
        Menu("camp donut", "R$ 9,09", R.drawable.img_donut9),
        Menu("sad donut", "R$ 8,88", R.drawable.img_donut10)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.rvList.layoutManager = GridLayoutManager(this, 2)
        binding.rvList.adapter = MenuAdapter(menus)
    }
}

