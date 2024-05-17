package com.example.nbc_standard_week7.presentation.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbc_standard_week7.databinding.FragmentFavoriteBinding
import com.example.nbc_standard_week7.presentation.mapper.FoodItemModel
import com.example.nbc_standard_week7.presentation.ui.list.FoodItemAdapter
import com.example.nbc_standard_week7.presentation.ui.list.ListFragment
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModel
import com.example.nbc_standard_week7.presentation.viewmodel.FoodItemViewModelFactory

class FavoriteFragment : Fragment(), FavItemAdapter.OnSwitchChangeListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodItemViewModel by activityViewModels()

    private val adapter: FavItemAdapter by lazy {
        FavItemAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeView()
        adapter.setOnSwitchChangeListener(this)
    }

    override fun onSwitchChanged(item: FoodItemModel, isChecked: Boolean) {
        val currentList = viewModel.getB553748SearchResult.value?.toMutableList() ?: return
        val position = currentList.indexOfFirst { it.image == item.image }

        if (position != -1) {
            currentList[position].like = isChecked
            viewModel.setB553748List(currentList)
        }
    }

    private fun makeView() {
        viewModel.getB553748SearchResult.observe(viewLifecycleOwner) {
            val likedItems = it.filter { item -> item.like }
            adapter.setViewItems(likedItems)
        }

        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}