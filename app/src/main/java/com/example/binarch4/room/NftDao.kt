package com.example.binarch4.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NftDao {
    @Query("SELECT * FROM Nft")
    fun getAllNft(): List<Nft>

    @Insert(onConflict = REPLACE)
    fun insertNft(nft: Nft):Long

    @Update
    fun updateNft(nft: Nft?):Int

    @Delete
    fun deleteNft(nft: Nft):Int
}