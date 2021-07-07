package com.dicoding.estomihi.githubsearchuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.dicoding.estomihi.githubsearchuser.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row_user.view.*

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
  private lateinit var onItemClickCallback: OnItemClickCallback
  var listFavorites = ArrayList<UserData>()
    set(listFavorites){
        if(listFavorites.size >= 0){
            this.listFavorites.clear()
        }
        this.listFavorites.addAll(listFavorites)
        notifyDataSetChanged()
    }
    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(userData: UserData){
            with(itemView){
                Picasso.get()
                    .load(userData.avatar)
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .error(R.drawable.ic_baseline_account_circle_24)
                    .into(item_row_avatar)
                item_row_name.text = userData.username

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(userData)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return FavoriteViewHolder((view))
    }
    override fun getItemCount(): Int = this.listFavorites.size

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback{
        fun onItemClicked(data: UserData)
    }
}