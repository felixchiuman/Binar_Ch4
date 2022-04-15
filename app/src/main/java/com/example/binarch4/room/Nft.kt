package com.example.binarch4.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nft_item")
data class Nft(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "hour") var hour: Int? = null,
    @ColumnInfo(name = "minute") var minutes: Int? = null
)
