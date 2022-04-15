package com.example.binarch4.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NftDao {
//    @Query("SELECT * FROM Nft")
//    fun getAllNft(): List<Nft>

    @Insert(onConflict = REPLACE)
    suspend fun insertNft(nft: Nft):Long

    @Update
    suspend fun updateNft(nft: Nft?):Int

    @Delete
    suspend fun deleteNft(nft: Nft):Int

    @Query("SELECT * FROM nft_item")
    fun getAllNftItems(): LiveData<List<Nft>>
}