package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
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
        Log.d(TAG, "onCreateView")
        _binding = FragmentImageSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        searchImage()
        makeView()
    }

    private fun searchImage() {
        binding.btnSearch.setOnClickListener {

            this.hideKeyboard()

            val targetText = binding.etvSearch.text.toString()
            communicateNetWork(targetText)
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
            Log.d("Parsing Kakao ::", it.toString())
            adapter.searchItems(it)
        }

        viewModel.getMyItemList(requireContext())
        viewModel.getMyItemResult.observe(requireActivity()) {
            Log.d("1frag", "DB데이터에 변화가 감지되었습니당.")
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

        Log.d("fragment", check.toString())
        if (check) {
            Log.d("fragment", "보관함에서 지워주세요.")
            viewModel.deletedItem(item, requireContext())
        } else {
            Log.d("fragment", "보관함에 추가해주세요.")
            viewModel.insertItem(item, requireContext())
        }
    }
}