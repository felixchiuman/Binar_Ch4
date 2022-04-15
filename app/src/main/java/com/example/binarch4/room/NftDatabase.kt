package com.example.binarch4.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nft::class],version = 1)
abstract class NftDatabase: RoomDatabase() {
    abstract fun getNftDao(): NftDao

    companion object{
        @Volatile
        private var instance: NftDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: getInstance(context)//.also{ instance = it} -> error
        }

        fun getInstance(context: Context){
            Room.databaseBuilder(context.applicationContext,
            NftDatabase::class.java,"Nft.db").build()
//            if(INSTANCE == null){
//                synchronized(NftDatabase::class){
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,NftDatabase::class.java,"Nft.db").build()
//                }
//            }
        }

//        fun destroyInstance(){
//            INSTANCE = null
//        }
    }
}