package com.example.sipnosisbuku

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListBukuAdapter(val listBuku: ArrayList<Buku>) : RecyclerView.Adapter<ListBukuAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_buku, viewGroup, false)
        return ListViewHolder(view)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val Buku = listBuku[position]
        Glide.with(holder.itemView.context)
            .load(Buku.foto)
            .apply(RequestOptions())
            .into(holder.imgPhoto)
        holder.tvJudul.text = Buku.judul
        holder.tvPenulis.text = Buku.penulis
        holder.btnFavorite.setOnClickListener { Toast.makeText(holder.itemView.context, "Favorite " + listBuku[holder.adapterPosition].judul, Toast.LENGTH_SHORT).show() }
        holder.btnShare.setOnClickListener { Toast.makeText(holder.itemView.context, "Share " + listBuku[holder.adapterPosition].judul, Toast.LENGTH_SHORT).show() }

        val mContext = holder.itemView.context
        holder.itemView.setOnClickListener {
            val DetailBuku = Intent(mContext, DetailSipnosis::class.java)
            DetailBuku.putExtra(DetailSipnosis.EXTRA_FOTO, Buku.foto)
            DetailBuku.putExtra(DetailSipnosis.EXTRA_JUDUL, Buku.judul)
            DetailBuku.putExtra(DetailSipnosis.EXTRA_PENULIS, Buku.penulis)
            DetailBuku.putExtra(DetailSipnosis.EXTRA_HALAMAN, Buku.halaman)
            DetailBuku.putExtra(DetailSipnosis.EXTRA_SIPNOSIS, Buku.sipnosis)
            DetailBuku.putExtra(DetailSipnosis.EXTRA_RILIS, Buku.rilis)
            mContext.startActivity(DetailBuku)
        }
    }

    override fun getItemCount(): Int {
        return listBuku.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvJudul: TextView = itemView.findViewById(R.id.item_name)
        var tvPenulis: TextView = itemView.findViewById(R.id.item_penulis)
        var imgPhoto: ImageView = itemView.findViewById(R.id.item_photo)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }

}