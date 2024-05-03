package com.example.nbc__imagecollector__typea.view.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc__imagecollector__typea.databinding.FragmentImageSearchBinding
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.view.adapter.RecylcerViewAdapter
import com.example.nbc__imagecollector__typea.view.util.UtilityKeyboard.hideKeyboard
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModel
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModelFactory
import kotlinx.coroutines.launch

class ImageSearchFragment : Fragment() {

    private val TAG = "ImageSearchFragment"

    private var _binding: FragmentImageSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private val adapter = RecylcerViewAdapter(TAG)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        searchImage()
        makeView()
        loadTextData()
    }

    private fun searchImage() {
        binding.btnSearch.setOnClickListener {
            shareEditTextData(binding.etvSearch.text.toString())

            this.hideKeyboard()

            val targetText = binding.etvSearch.text.toString()
            communicateNetWork(targetText)
        }
    }

    private fun shareEditTextData(text: String) {
        viewModel.setInputText(text)

        viewModel.getInputText.observe(this) {
            Log.d("shared", it.toString())
            val preferences = this.activity?.getSharedPreferences("pref", 0)
            val edit = preferences?.edit()
            edit?.putString("input", it)
            edit?.apply()
        }
    }

    private fun communicateNetWork(targetText: String) = lifecycleScope.launch {
        viewModel.getImageList  (
            targetText,
            "accuracy",
            1,
            10
        )
    }

    private fun makeView() {
        viewModel.getSearchResult.observe(viewLifecycleOwner) {
            adapter.searchItems(it)
        }

        viewModel.getMyItemList(requireContext())
        viewModel.getMyItemResult.observe(requireActivity()) {
            adapter.getRoomItems(it)
        }

        binding.rvSearch.adapter = adapter
        binding.rvSearch.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter.itemClick = object : RecylcerViewAdapter.ItemClick {
            override fun onClick(view: View, item: KakaoDocuments) {
                selectImage(item)
            }
        }
    }

    private fun selectImage(item: KakaoDocuments) {
        val check = viewModel.getSearchItemCheck(item.thumbnail_url, requireContext())
        if (check) {
            viewModel.deletedItem(item, requireContext())
        } else {
            viewModel.insertItem(item, requireContext())
        }
    }

    private fun loadTextData() {
        val preferences = this.activity?.getSharedPreferences("pref", 0)
        val text = preferences?.getString("input", "왜 저장 안됨")
        Log.d("shared", "불러올 때 text 상태 : ${text}")

        binding.etvSearch.setText(text)
    }
}