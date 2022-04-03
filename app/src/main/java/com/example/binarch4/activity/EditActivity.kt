package com.example.binarch4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarch4.R
import com.example.binarch4.room.Nft
import com.example.binarch4.room.NftDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditActivity : AppCompatActivity() {
    var mDb: NftDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        mDb = NftDatabase.getInstance(this)

        val objectNft = intent.getParcelableExtra<Nft>("nft")

        et_name_nft.editText?.setText(objectNft?.name)
        et_date_nft.editText?.setText(objectNft?.date)
        et_time_hour_nft.editText?.setText(objectNft?.hour)
        et_time_minute_nft.editText?.setText(objectNft?.minutes)

        btn_save.setOnClickListener {
            objectNft?.name = et_name_nft.editText?.text.toString()
            objectNft?.date = et_date_nft.editText?.text.toString()
            objectNft?.hour = et_time_hour_nft.editText?.text.toString()
            objectNft?.minutes = et_time_minute_nft.editText?.text.toString()

            GlobalScope.async {
                val result = objectNft?.let { it1 -> mDb?.nftDao()?.updateNft(it1) }

                runOnUiThread {
                    if(result!=0){
                        Toast.makeText(this@EditActivity,"Sukses mengubah ${objectNft?.name}", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@EditActivity,"Gagal mengubah ${objectNft.name}", Toast.LENGTH_LONG).show()
                    }

                    finish()
                }
            }
        }
    }
}