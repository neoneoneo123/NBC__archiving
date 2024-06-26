package com.example.nbc__imagecollector__typea.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.repository.SearchRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepositoryImpl: SearchRepositoryImpl) : ViewModel() {

    private val _getSearchResult: MutableLiveData<List<KakaoDocuments>> = MutableLiveData()
    val getSearchResult: LiveData<List<KakaoDocuments>> get() = _getSearchResult

    private val _getMyItemResult: MutableLiveData<List<KakaoDocuments>> = MutableLiveData()
    val getMyItemResult: LiveData<List<KakaoDocuments>> get() = _getMyItemResult

    private val _getInputText: MutableLiveData<String> = MutableLiveData()
    val getInputText : LiveData<String> get() = _getInputText


    /**
     * 검색 query를 받아 repository에 데이터를 요청하여 LiveData에 저장하는 함수입니다.
     */
    fun getImageList(query: String, sort: String, page: Int, size: Int) = viewModelScope.launch {
        _getSearchResult.value = searchRepositoryImpl.search(query, sort, page, size).kakaoDocuments
    }

    /**
     * repository에 특정 데이터를 추가 요청하는 함수입니다.
     */
    fun insertItem(item: KakaoDocuments, context: Context) {
        searchRepositoryImpl.insert(item, context)
    }

    /**
     * repository에 특정 데이터를 삭제 요청하는 함수입니다.
     */
    fun deletedItem(item: KakaoDocuments, context: Context) {
        searchRepositoryImpl.delete(item, context)
    }

    /**
     * repository에 특정 데이터의 존재 유무 확인을 요청하는 함수입니다.
     * true/false로 반환합니다.
     */
    fun getSearchItemCheck(thumbnail_url: String, context: Context) : Boolean {
        return searchRepositoryImpl.check(thumbnail_url, context)
    }

    /**
     * Room DB에 존재하는 데이터를 요청하여 LiveData에 저장하는 함수입니다.
     */
    fun getMyItemList(context: Context) {
        viewModelScope.launch {
            searchRepositoryImpl.searchRoom(context).collect { items ->
                _getMyItemResult.value = items
            }
        }
    }

    /**
     * 입력된 검색어를 LiveData에 저장하는 함수입니다.
     */
    fun setInputText(text: String) {
        _getInputText.value = text
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {

    /**
     * 모든 fragment에서 하나의 viewModel을 사용할 수 있도록 처리하는 object입니다.
     */
    companion object {
        private val repository = SearchRepositoryImpl()
        fun newInstance(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                        @Suppress("UNCHECKED_CASE")
                        return SearchViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}