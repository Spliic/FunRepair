package com.example.abschlussaufgabe.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.abschlussaufgabe.ui.OnboardOneFragment
import com.example.abschlussaufgabe.ui.OnboardThreeFragment
import com.example.abschlussaufgabe.ui.OnboardTwoFragment
import com.example.abschlussaufgabe.ui.RegisterFragment
import com.example.abschlussaufgabe.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {




    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnboardOneFragment()
            1 -> OnboardTwoFragment()
            2 -> OnboardThreeFragment()
            3 -> RegisterFragment()

            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}