package com.example.nbc__imagecollector__typea.view.ui

import android.os.Bundle
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

    private val viewModel: SearchViewModel by viewModels({ requireActivity() }) {
        SearchViewModelFactory.newInstance()
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
        makeView()
    }

    /**
     * RecyclerView를 그리는 함수입니다.
     */
    private fun makeView() {
        viewModel.getMyItemList(requireContext())
        viewModel.getMyItemResult.observe(viewLifecycleOwner) {
            adapter.setRecyclerViewItems(it)
        }

        binding.rvBox.adapter = adapter
        binding.rvBox.layoutManager = GridLayoutManager(requireContext(), 2)

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
}