package com.example.binarch4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarch4.AddListener
import com.example.binarch4.R
import com.example.binarch4.room.Nft
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.btn_save
import kotlinx.android.synthetic.main.activity_add.et_name_nft

class AddActivity (var addListener: AddListener): AppCompatActivity() {

//    var mDb: NftDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

//        mDb = NftDatabase.getInstance(this)

        btn_save.setOnClickListener {

                val name = et_name_nft.editText?.text.toString()
                val date = et_date_nft.editText?.text.toString()
                val hour = et_time_hour_nft.editText?.text.toString()
                val min = et_time_minute_nft.editText?.text.toString()

            if (name.isEmpty()||date.isEmpty()||hour == ""||min==""){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

            val item = Nft(name, date, hour, min.toInt())
            addListener.onAddButtonClicked(item)

//            GlobalScope.async {
//                val result = mDb?.getNftDao()?.insertNft(objectStudent)
//                runOnUiThread {
//                    if(result != 0.toLong()){
//                        //sukses
//                        Toast.makeText(this@AddActivity,"Sukses menambahkan ${objectStudent.name}",
//                            Toast.LENGTH_LONG).show()
//                    }else{
//                        //gagal
//                        Toast.makeText(this@AddActivity,"Gagal menambahkan ${objectStudent.name}",
//                            Toast.LENGTH_LONG).show()
//                    }
//                    finish()
//                }
//            }
        }
    }
}