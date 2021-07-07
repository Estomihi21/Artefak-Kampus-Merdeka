package com.dicoding.estomihi.githubuser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.estomihi.githubuser.model.User
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = arrayListOf<User>()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val user = getItem(position) as User
        viewHolder.bind(user)
        return itemView

    }

    override fun getItem(position: Int): Any = users[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = users.size


    private inner class ViewHolder internal constructor(view: View) {
        private val txtName: TextView = view.findViewById(R.id.tv_name)
        private val txtCompany: TextView = view.findViewById(R.id.tv_company)
        private val imgAvatar: CircleImageView = view.findViewById(R.id.img_avatar)

        internal fun bind(user: User) {
            txtName.text = user.name
            txtCompany.text = user.company
            Glide.with(context)
                .load(user.avatar)
                .apply(RequestOptions().override(90,90))
                .into(imgAvatar)
        }
    }
}