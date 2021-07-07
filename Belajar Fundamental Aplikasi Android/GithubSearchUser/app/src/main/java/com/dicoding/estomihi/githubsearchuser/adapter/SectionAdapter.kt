package com.dicoding.estomihi.githubsearchuser.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.estomihi.githubsearchuser.R
import com.dicoding.estomihi.githubsearchuser.fragment.MainFragment

class SectionAdapter (private val mContext: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var username: String? = "username"

    @StringRes
    private val TAB_TITLE = intArrayOf(R.string.followers, R.string.following)

    fun setData(usernameGithub :  String) {
        username = usernameGithub
    }

    override fun getItem(position: Int): Fragment {
        return MainFragment.newInstance(position + 1)
    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }

    override fun getCount(): Int {
        return 2
    }


}