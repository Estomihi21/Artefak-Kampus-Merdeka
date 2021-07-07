package com.dicoding.estomihi.githubsearchuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.dicoding.estomihi.githubsearchuser.R
import kotlinx.android.synthetic.main.item_detail_user.view.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val mData = ArrayList<UserData>()

    fun setData(items: ArrayList<UserData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_user, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userDetail: UserData) {
            with(itemView) {
                Glide.with(context)
                    .load(userDetail.avatar)
                    .into(detail_avatar)
                detail_name.text = userDetail.name.toString()
                detail_username.text = userDetail.login.toString()
                detail_location.text = userDetail.location
                detail_company.text = userDetail.company
                detail_repository.text = userDetail.repository.toString()
                detail_followers.text = userDetail.followers.toString()
                detail_following.text = userDetail.following.toString()
            }
        }
    }
}