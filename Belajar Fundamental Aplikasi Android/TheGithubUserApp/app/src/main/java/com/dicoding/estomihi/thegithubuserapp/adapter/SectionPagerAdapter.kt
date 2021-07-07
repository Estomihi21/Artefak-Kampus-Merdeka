package com.dicoding.estomihi.thegithubuserapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.estomihi.thegithubuserapp.fragment.MainFragment

class SectionPagerAdapter(activity: AppCompatActivity, private val username: String?) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return MainFragment.newInstance(position + 1, username)
    }

    override fun getItemCount(): Int {
        return 2
    }

}