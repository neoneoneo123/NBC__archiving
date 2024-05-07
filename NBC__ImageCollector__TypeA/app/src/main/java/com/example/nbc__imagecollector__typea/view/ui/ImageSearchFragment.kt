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

    /**
     * 검색 버튼 클릭 시 발생하는 행위들이 정의된 함수입니다.
     * - Shared Preferences에 데이터 저장
     * - keyboard 숨김
     * - 검색어를 사용하여 API와 통신
     */
    private fun searchImage() {
        binding.btnSearch.setOnClickListener {
            shareEditTextData(binding.etvSearch.text.toString())

            this.hideKeyboard()

            val targetText = binding.etvSearch.text.toString()
            communicateNetWork(targetText)
        }
    }

    /**
     * 검색어가 처리되었을 때 Shared Preferences에 데이터를 저장하는 함수입니다.
     */
    private fun shareEditTextData(text: String) {
        viewModel.setInputText(text)

        viewModel.getInputText.observe(viewLifecycleOwner) {
            val preferences = this.activity?.getSharedPreferences("pref", 0)
            val edit = preferences?.edit()
            edit?.putString("input", it)
            edit?.apply()
        }
    }

    /**
     * API 통신을 위해 viewModel로 데이터를 전달하는 함수입니다.
     */
    private fun communicateNetWork(targetText: String) = lifecycleScope.launch {
        viewModel.getImageList  (
            targetText,
            "accuracy",
            1,
            10
        )
    }

    /**
     * RecyclerView를 그리는 함수입니다.
     */
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

    /**
     * 이미지 선택 시 Room DB 상 데이터 추가/삭제를 위해 viewModel에 요청하는 함수입니다.
     */
    private fun selectImage(item: KakaoDocuments) {
        val check = viewModel.getSearchItemCheck(item.thumbnail_url, requireContext())
        if (check) {
            viewModel.deletedItem(item, requireContext())
        } else {
            viewModel.insertItem(item, requireContext())
        }
    }

    /**
     * Shared Preferences에 저장되어 있는 마지막 검색어를 표시하는 함수입니다.
     */
    private fun loadTextData() {
        val preferences = this.activity?.getSharedPreferences("pref", 0)
        val text = preferences?.getString("input", "")

        binding.etvSearch.setText(text)
    }
}