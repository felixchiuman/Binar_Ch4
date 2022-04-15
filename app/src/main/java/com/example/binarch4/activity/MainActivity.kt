package com.example.binarch4.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.binarch4.R
import com.example.binarch4.repositories.NftRepository
import com.example.binarch4.room.NftAdapter
import com.example.binarch4.room.NftDatabase
import com.example.binarch4.ui.NftViewModel
import com.example.binarch4.ui.NftViewMofelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private var mDB : NftDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = NftDatabase(this)
        val repository = NftRepository(database as NftDatabase)
        val factory = NftViewMofelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(NftViewModel::class.java)

        val adapter = NftAdapter(listOf(),viewModel)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.getAllNftItems().observe(this, Observer {
            adapter.listNft = it
            adapter.notifyDataSetChanged()
        })

//        mDB = NftDatabase.getInstance(this)

//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        fetchData()

        tv_sign_out.setOnClickListener {
            val activityLogin = Intent(this, LoginActivity::class.java)
            startActivity(activityLogin)
            finish()
        }
        fabAdd.setOnClickListener {
            val keActivityAdd = Intent(this, AddActivity::class.java)
            startActivity(keActivityAdd)
        }
    }

//    override fun onResume() {
//        super.onResume()
//        fetchData()
//    }

//    fun fetchData(){
//        GlobalScope.launch {
//            val listStudent = mDB?.nftDao()?.getAllNft()
//
//            runOnUiThread{
//                listStudent?.let {
//                    val adapter = NftAdapter(it)
//                    recyclerView.adapter = adapter
//                }
//            }
//        }
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        NftDatabase.destroyInstance()
//    }
}