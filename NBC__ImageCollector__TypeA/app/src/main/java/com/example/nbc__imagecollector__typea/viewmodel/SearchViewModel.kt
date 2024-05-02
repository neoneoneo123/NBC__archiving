package com.example.nbc__imagecollector__typea.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nbc__imagecollector__typea.service.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.service.repository.SearchRepository

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _getSearchResult: MutableLiveData<List<KakaoDocuments>> = MutableLiveData()
    val getSearchResult: LiveData<List<KakaoDocuments>> get() = _getSearchResult

}

class SearchViewModelProviderFactory(
    private val searchRepository: SearchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(searchRepository) as T
    }
}