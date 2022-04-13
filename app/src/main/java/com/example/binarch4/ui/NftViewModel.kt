package com.example.binarch4.ui

import androidx.lifecycle.ViewModel
import com.example.binarch4.repositories.NftRepository
import com.example.binarch4.room.Nft
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NftViewModel(
    private val repository: NftRepository
): ViewModel() {
    fun insert(item: Nft) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }

    fun delete(item: Nft) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun update(item: Nft) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(item)
    }

    fun getAllNftItems() = repository.getAllNftItems()
}