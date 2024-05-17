package com.example.nbc_standard_week7.presentation.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc_standard_week7.databinding.FragmentListBinding
import com.example.nbc_standard_week7.presentation.model.FoodItem
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), FoodItemAdapter.OnSwitchChangeListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val shardViewModel: FoodItemViewModel by activityViewModels()

    private val adapter: FoodItemAdapter by lazy {
        FoodItemAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeView()
        searchItems()
        observingItems()
    }

    override fun onSwitchChanged(item: FoodItem, isChecked: Boolean) {
        val currentList = shardViewModel.getFoodItemSearchResult.value?.toMutableList() ?: return
        val position = currentList.indexOfFirst { it.image == item.image }

        if (position != -1) {
            currentList[position].like = isChecked
            shardViewModel.setFoodItemList(currentList)
        }
    }

    private fun observingItems() {
        shardViewModel.getFoodItemSearchResult.observe(viewLifecycleOwner) {
            adapter.setViewItems(it)
        }
    }

    private fun searchItems() {
        binding.btnSearch.setOnClickListener {
            val targetText = binding.tvEdit.text.toString()
            shardViewModel.getFoodItemList(1, 10, targetText)
        }
    }

    private fun makeView() {
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())
        adapter.setOnSwitchChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d("리스트 프래그먼트", "onResume")
        makeView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}