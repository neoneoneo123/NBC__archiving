package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

    private val viewModel: SearchViewModel by viewModels({ requireActivity() }) {
        SearchViewModelFactory.newInstance()
    }

    private val adapter = RecylcerViewAdapter(TAG)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        viewModel.getInputText.observe(viewLifecycleOwner) {
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
            adapter.setRecyclerViewItems(it)
        }

        viewModel.getMyItemList(requireContext())
        viewModel.getMyItemResult.observe(viewLifecycleOwner) {
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
        val text = preferences?.getString("input", "")

        binding.etvSearch.setText(text)
    }
}