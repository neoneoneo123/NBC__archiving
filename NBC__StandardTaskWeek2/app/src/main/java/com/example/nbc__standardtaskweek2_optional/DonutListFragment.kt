package com.example.nbc__standardtaskweek2_optional

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc__standardtaskweek2_optional.databinding.DonutListFragmentBinding

class DonutListFragment : Fragment() {

    lateinit var binding: DonutListFragmentBinding

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonutListFragmentBinding.inflate(layoutInflater)
        initializeViews()

        var view = binding.root

        val itemBtn = view.findViewById<FrameLayout>(R.id.fl_item)
        //TODO : 누르면 이벤트가 일어나게 하고싶은데..


        return view
    }

    private fun initializeViews() {
        binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvList.adapter = MenuAdapter(menus)
    }
}