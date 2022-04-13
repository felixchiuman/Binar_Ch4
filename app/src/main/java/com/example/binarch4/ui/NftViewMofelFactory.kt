package com.example.binarch4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.binarch4.repositories.NftRepository

class NftViewMofelFactory(
    private val repository: NftRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NftViewModel(repository) as T
    }
}