package com.example.bliapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bliapp.models.News
import com.example.bliapp.repositories.NewsRepository
import com.example.bliapp.utils.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias NewsList = List<News>

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _topNews = MutableStateFlow<RequestState<NewsList>>(RequestState.Idle)
    val topNews: StateFlow<RequestState<NewsList>> = _topNews

    private val _newNews = MutableStateFlow<RequestState<NewsList>>(RequestState.Idle)
    val newNews: StateFlow<RequestState<NewsList>> = _newNews

    private val _selectedNews = MutableStateFlow<RequestState<News>>(RequestState.Idle)
    val selectedNews: StateFlow<RequestState<News>> = _selectedNews

    fun getTopNews() {
        _topNews.value = RequestState.Loading
        try {
            viewModelScope.launch {
                newsRepository.getTopNews().collect {
                    _topNews.value = RequestState.Success(it)
                }
            }
        } catch (err: Exception) {
            _topNews.value = RequestState.Error(err)
        }
    }

    fun getNewNews() {
        _newNews.value = RequestState.Loading
        try {
            viewModelScope.launch {
                newsRepository.getNewNews().collect {
                    _newNews.value = RequestState.Success(it)
                }
            }
        } catch (err: Exception) {
            _newNews.value = RequestState.Error(err)
        }
    }

    fun getNewsById(id: Int) {
        _selectedNews.value = RequestState.Loading
        try {
            viewModelScope.launch {
                newsRepository.getNewsById(id).collect {
                    _selectedNews.value = RequestState.Success(it)
                }
            }
        } catch (err: Exception) {
            _selectedNews.value = RequestState.Error(err)
        }
    }

}