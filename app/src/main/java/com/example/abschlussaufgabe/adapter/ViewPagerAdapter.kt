package com.example.abschlussaufgabe.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.abschlussaufgabe.ui.OnboardOneFragment
import com.example.abschlussaufgabe.ui.OnboardThreeFragment
import com.example.abschlussaufgabe.ui.OnboardTwoFragment
import java.lang.IllegalArgumentException

class ViewPagerAdapter(fragmentActivity: FragmentActivity, fragmentList: List<Fragment>): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardOneFragment()
            1 -> OnboardTwoFragment()
            2 -> OnboardThreeFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }

    }
}