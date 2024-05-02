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
import com.example.nbc__imagecollector__typea.model.KakaoDAO
import com.example.nbc__imagecollector__typea.model.KakaoDatabase
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.view.adapter.RecylcerViewAdapter
import com.example.nbc__imagecollector__typea.view.util.UtilityKeyboard.hideKeyboard
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModel
import com.example.nbc__imagecollector__typea.viewmodel.SearchViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageSearchFragment : Fragment() {

    private var _binding: FragmentImageSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    lateinit var kakaoDao: KakaoDAO

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
    }

    private fun searchImage() {
        binding.btnSearch.setOnClickListener {

            this.hideKeyboard()

            val targetText = binding.etvSearch.text.toString()
            communicateNetWork(targetText)
        }

        viewModel.getSearchResult.observe(requireActivity()) {
            Log.d("Parsing Kakao ::", it.toString())
            val items = it

            makeView(items)
        }
    }

    private fun communicateNetWork(targetText: String) = lifecycleScope.launch {
        viewModel.getImageList  (
            targetText,
            "accuracy",
            1,
            80
        )
    }

    private fun makeView(items: List<KakaoDocuments>) {
        val adapter = RecylcerViewAdapter(items)
        binding.rvSearch.adapter = adapter
        binding.rvSearch.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter.itemClick = object : RecylcerViewAdapter.ItemClick {
            override fun onClick(item: KakaoDocuments) {
                selectImage(item)
            }
        }
    }

    private fun selectImage(item: KakaoDocuments) {
        kakaoDao = KakaoDatabase.getDatabase(requireContext()).getKakaoDao()

        CoroutineScope(Dispatchers.IO).launch {
            kakaoDao.insertItem(item)
        }
    }
}