package com.example.xpenso

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class SectionsPageAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    private val FragmentList: MutableList<Fragment> = ArrayList()
    private val FragmentTitleList: MutableList<String> = ArrayList()
    fun addFragment(fragment: Fragment, title: String) {
        FragmentList.add(fragment)
        FragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return FragmentTitleList[position]
    }

    override fun getItem(position: Int): Fragment {
        return FragmentList[position]
    }

    override fun getCount(): Int {
        return FragmentList.size
    }
}