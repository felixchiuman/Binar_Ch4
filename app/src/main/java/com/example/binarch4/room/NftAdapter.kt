package com.example.binarch4.room

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.binarch4.R
import com.example.binarch4.activity.EditActivity
import com.example.binarch4.activity.MainActivity
import kotlinx.android.synthetic.main.nft_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NftAdapter(val listNft : List<Nft>) : RecyclerView.Adapter<NftAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.nft_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listNft.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_name.text = listNft[position].name
        holder.itemView.tv_date.text = listNft[position].date
        holder.itemView.tv_hour.text = listNft[position].hour
        holder.itemView.tv_min.text = listNft[position].minutes

        holder.itemView.ivEdit.setOnClickListener {
            val intentKeEditActivity = Intent(it.context,
                EditActivity::class.java)

            intentKeEditActivity.putExtra("student",listNft[position])
            it.context.startActivity(intentKeEditActivity)
        }

        holder.itemView.ivDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                val mDb = NftDatabase.getInstance(holder.itemView.context)

                GlobalScope.async {
                    val result = mDb?.nftDao()?.deleteNft(listNft[position])

                    (holder.itemView.context as MainActivity).runOnUiThread {
                        if (result!=0){
                            Toast.makeText(it.context,"Data ${listNft[position].name} berhasil dihapus",
                                Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(it.context,"Data ${listNft[position].name} Gagal dihapus",
                                Toast.LENGTH_LONG).show()
                        }
                    }

                    (holder.itemView.context as MainActivity).fetchData()
                }
            }.setNegativeButton("Tidak"
            ) { p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin ingin menghapus data ${listNft[position].name}").setTitle("Konfirmasi Hapus").create().show()
        }
    }
}