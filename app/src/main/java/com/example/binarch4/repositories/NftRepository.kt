package com.example.binarch4.repositories

import com.example.binarch4.onboarding.OnBoardingItem
import com.example.binarch4.room.Nft
import com.example.binarch4.room.NftDatabase

class NftRepository(
    private val db : NftDatabase
) {
    suspend fun insert(item: Nft) = db.nftDao().insertNft(item)

    suspend fun delete(item: Nft) = db.nftDao().deleteNft(item)

    suspend fun update(item: Nft) = db.nftDao().updateNft(item)

    fun getAllNftItems() = db.nftDao().getAllNft()
}