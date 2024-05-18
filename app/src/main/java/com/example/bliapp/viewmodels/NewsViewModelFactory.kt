package com.example.bliapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bliapp.repositories.NewsRepository

class NewsViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = NewsViewModel(NewsRepository()) as T
}