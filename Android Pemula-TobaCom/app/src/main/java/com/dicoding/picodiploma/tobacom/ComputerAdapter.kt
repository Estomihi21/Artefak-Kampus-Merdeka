package com.dicoding.picodiploma.tobacom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ComputerAdapter(private val listComputer: ArrayList<Computer>): RecyclerView.Adapter<ComputerAdapter.ListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_computer, parent,false )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val Computer = listComputer[position]

        Glide.with(holder.itemView.context)
                .load(Computer.photo)
                .apply(RequestOptions().override(55,55))
                .into(holder.imgPhoto)
        holder.tvName.text = Computer.name

        val mContext = holder.itemView.context
        holder.itemView.setOnClickListener{
            val moveDetail= Intent(mContext,ComputerDetail::class.java)
            moveDetail.putExtra(ComputerDetail.EXTRA_NAME,Computer.name)
            moveDetail.putExtra(ComputerDetail.EXTRA_PHOTO,Computer.photo)
            moveDetail.putExtra(ComputerDetail.EXTRA_DETAIL,Computer.detail)
            mContext.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int {
        return listComputer.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

}