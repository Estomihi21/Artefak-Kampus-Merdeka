package com.dicoding.estomihi.githubsearchuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.dicoding.estomihi.githubsearchuser.DetailActivity
import com.dicoding.estomihi.githubsearchuser.R
import kotlinx.android.synthetic.main.item_row_user.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    private val mData = ArrayList<UserData>()

    fun setData(items: ArrayList<UserData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserData) {
            with(itemView) {
                Glide.with(context)
                    .load(user.avatar)
                    .into(item_row_avatar)
                item_row_name.text = user.login

                itemView.setOnClickListener {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_USERNAME, user.login)
                    }.run {
                        context.startActivity(this)
                    }
                }
            }
        }
    }

}