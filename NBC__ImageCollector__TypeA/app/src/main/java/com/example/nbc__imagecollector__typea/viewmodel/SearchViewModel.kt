package com.example.nbc__imagecollector__typea.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.repository.SearchRepository
import com.example.nbc__imagecollector__typea.repository.SearchRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepositoryImpl: SearchRepositoryImpl) : ViewModel() {

    private val _getSearchResult: MutableLiveData<List<KakaoDocuments>> = MutableLiveData()
    val getSearchResult: LiveData<List<KakaoDocuments>> get() = _getSearchResult

    fun getImageList(query: String, sort: String, page: Int, size: Int) = viewModelScope.launch {
        _getSearchResult.value = searchRepositoryImpl.search(query, sort, page, size).kakaoDocuments
        Log.d("viewModel", getSearchResult.value.toString())
    }

    fun getSelectecItem(item: KakaoDocuments, context: Context) {
        searchRepositoryImpl.insert(item, context)
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val repository = SearchRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}