package com.dicoding.estomihi.thegithubuserapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.estomihi.thegithubuserapp.DetailUserActivity
import com.dicoding.estomihi.thegithubuserapp.R
import com.dicoding.estomihi.thegithubuserapp.data.UserData
import com.dicoding.estomihi.thegithubuserapp.databinding.ItemRowUserBinding

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    private val listUser = ArrayList<UserData>()

    fun setData(items: ArrayList<UserData>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ListUserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val userDetail = Intent(mContext, DetailUserActivity::class.java)
            userDetail.putExtra(DetailUserActivity.EXTRA_LOGIN, user.login)
            userDetail.putExtra(DetailUserActivity.EXTRA_ID, user.id)
            userDetail.putExtra(DetailUserActivity.EXTRA_AVATAR, user.avatar_url)
            mContext.startActivity(userDetail)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUserBinding.bind(itemView)

        internal fun bind(user : UserData) {
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .centerCrop()
                .into(binding.ivUser)

            binding.tvName.text = user.login
        }
    }
}