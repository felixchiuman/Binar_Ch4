package com.example.binarch4.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nft::class],version = 1)
abstract class NftDatabase: RoomDatabase() {
    abstract fun nftDao(): NftDao

    companion object{
        private var INSTANCE: NftDatabase? = null

        fun getInstance(context: Context): NftDatabase? {
            if(INSTANCE == null){
                synchronized(NftDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,NftDatabase::class.java,"Nft.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}