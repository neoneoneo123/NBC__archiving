package com.example.nbc__imagecollector__typea.viewmodel

import android.content.Context
import android.util.Log
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


    fun getImageList(query: String, sort: String, page: Int, size: Int) = viewModelScope.launch {
        _getSearchResult.value = searchRepositoryImpl.search(query, sort, page, size).kakaoDocuments
    }

    fun insertItem(item: KakaoDocuments, context: Context) {
        searchRepositoryImpl.insert(item, context)
    }

    fun deletedItem(item: KakaoDocuments, context: Context) {
        searchRepositoryImpl.delete(item, context)
    }

    fun getSearchItemCheck(thumbnail_url: String, context: Context) : Boolean {
        return searchRepositoryImpl.check(thumbnail_url, context)
    }

    fun getMyItemList(context: Context) {
        viewModelScope.launch {
            searchRepositoryImpl.searchRoom(context).collect { items ->
                _getMyItemResult.value = items
            }
        }
    }

    fun setInputText(text: String) {
        _getInputText.value = text
        Log.d("shared", "텍스트 설정 : ${getInputText.value}")
    }

}

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val repository = SearchRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}