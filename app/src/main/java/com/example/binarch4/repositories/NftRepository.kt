package com.example.binarch4.repositories

import com.example.binarch4.room.Nft
import com.example.binarch4.room.NftDatabase

class NftRepository(
    private val db : NftDatabase
) {
    suspend fun insert(item: Nft) = db.getNftDao().insertNft(item)

    suspend fun delete(item: Nft) = db.getNftDao().deleteNft(item)

    suspend fun update(item: Nft) = db.getNftDao().updateNft(item)

    fun getAllNftItems() = db.getNftDao().getAllNftItems()
}