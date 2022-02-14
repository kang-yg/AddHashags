package com.tagplus.addhashtags.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tagplus.addhashtags.view.FragmentMine
import com.tagplus.addhashtags.view.FragmentPopTags

class ViewPagerAdapter(fragmentActivity: FragmentActivity, var fragmentPopTags: FragmentPopTags, var fragmentMine: FragmentMine) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> fragmentPopTags
            1 -> fragmentMine
            else -> fragmentPopTags
        }
    }
}