package com.example.binarch4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarch4.R
import com.example.binarch4.room.Nft
import com.example.binarch4.room.NftDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.btn_save
import kotlinx.android.synthetic.main.activity_add.et_name_nft
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddActivity : AppCompatActivity() {

    var mDb: NftDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mDb = NftDatabase.getInstance(this)

        btn_save.setOnClickListener {

            val objectStudent = Nft(
                null,
                et_name_nft.editText?.text.toString(),
                et_date_nft.editText?.text.toString(),
                et_time_hour_nft.editText?.text.toString(),
                et_time_minute_nft.editText?.text.toString()
            )

            GlobalScope.async {
                val result = mDb?.nftDao()?.insertNft(objectStudent)
                runOnUiThread {
                    if(result != 0.toLong()){
                        //sukses
                        Toast.makeText(this@AddActivity,"Sukses menambahkan ${objectStudent.name}",
                            Toast.LENGTH_LONG).show()
                    }else{
                        //gagal
                        Toast.makeText(this@AddActivity,"Gagal menambahkan ${objectStudent.name}",
                            Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }
        }
    }
}