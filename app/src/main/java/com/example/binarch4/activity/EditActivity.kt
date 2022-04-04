package com.example.binarch4.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.R
import com.example.binarch4.room.Nft
import com.example.binarch4.room.NftDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.btn_save
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*
import kotlin.collections.ArrayList


class EditActivity : AppCompatActivity() {
    var mDb: NftDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        mDb = NftDatabase.getInstance(this)

        val objectNft = intent.getParcelableExtra<Nft>("nft")

        et_name.setText(objectNft?.name)
        et_date.setText(objectNft?.date)
        et_time_hour.setText(objectNft?.hour)
        et_time_minute.setText(objectNft?.minutes)

        btn_alarm.setOnClickListener {
            val permissionCheck = checkSelfPermission(android.Manifest.permission.SET_ALARM)
            
            if (permissionCheck == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }else{
                val hour = objectNft?.hour?.toInt()
                val min = objectNft?.minutes?.toInt()
                val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, "test")
                    putExtra(AlarmClock.EXTRA_HOUR, hour)
                    putExtra(AlarmClock.EXTRA_MINUTES, min)
                }
                if (intent.resolveActivity(packageManager) != null && hour!! < 24 && min!! < 60) {
                    startActivity(intent)
                }
//
//                val hour = et_time_hour.text.toString().toInt()
//                val min = et_time_minute.text.toString().toInt()
//                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent.putExtra(AlarmClock.EXTRA_HOUR, hour)
//                intent.putExtra(AlarmClock.EXTRA_MINUTES, min)
//
//                if (hour <24 && min <60){
//                    startActivity(intent)
                else{
                    Toast.makeText(this, "Alarm Set Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btn_save.setOnClickListener {
            objectNft?.name = et_name.text.toString()
            objectNft?.date = et_date.text.toString()
            objectNft?.hour = et_time_hour.text.toString()
            objectNft?.minutes = et_time_minute.text.toString()

                GlobalScope.async {
                    val result = mDb?.nftDao()?.updateNft(objectNft)

                    runOnUiThread {
                        if(result!=0){
                            Toast.makeText(this@EditActivity,"change changed ${objectNft?.name} successfully", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this@EditActivity,"change data ${objectNft?.name} failed", Toast.LENGTH_LONG).show()
                        }

                        finish()
                    }
                }
        }
    }
}