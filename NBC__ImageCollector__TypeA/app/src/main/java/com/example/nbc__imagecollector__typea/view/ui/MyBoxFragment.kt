package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc__imagecollector__typea.databinding.FragmentMyBoxBinding
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.view.adapter.RecylcerViewAdapter
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModel
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModelFactory


class MyBoxFragment : Fragment() {

    private val TAG = "MyBoxFragment"

    private var _binding: FragmentMyBoxBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private val adapter = RecylcerViewAdapter(TAG)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("my", "onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        makeView()
    }

    private fun makeView() {
        viewModel.getMyItemList(requireContext())
        if (viewModel.getMyItemResult.value?.isNotEmpty() == true) {
            viewModel.getMyItemResult.observe(requireActivity()) {
                adapter.searchItems(it)
            }
        }

        binding.rvBox.adapter = adapter
        binding.rvBox.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter.itemClick = object : RecylcerViewAdapter.ItemClick {
            override fun onClick(view: View, item: KakaoDocuments) {
                selectImage(item)
            }
        }
    }

    private fun selectImage(item: KakaoDocuments) {
        val check = viewModel.getSearchItemCheck(item.thumbnail_url, requireContext())

        Log.d("myBox", check.toString())
        if (check) {
            viewModel.deletedItem(item, requireContext())
        } else {
            viewModel.insertItem(item, requireContext())
        }
    }
}