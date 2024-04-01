package com.example.nbc__standardtaskweek2_optional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc__standardtaskweek2_optional.databinding.DonutDetailFragmentBinding

class DonutDetailFragment : Fragment() {

    lateinit var binding: DonutDetailFragmentBinding

    private val infos = listOf(
        Info("5.8g", "proteins"),
        Info("24g", "fat"),
        Info("404kcal", "calories"),
        Info("162,gg", "calcium"),
        Info("<5mg", "cholesterol"),
        Info("96g", "carbs")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonutDetailFragmentBinding.inflate(layoutInflater)
        initializeViews()

        return binding.root
    }

    private fun initializeViews() {
        binding.rvInfoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInfoList.adapter = InfoAdapter(infos)
    }
}