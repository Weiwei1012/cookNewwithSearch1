package com.cooking.merge.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cooking.merge.food_fragment.*


class TablayoutAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)  {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> Breakfastf()
            1 -> Ricef()
            2 -> Noodlesf()
            3 -> Soupf()
            4 -> Sidedishf()
            5 -> Chickenf()
            6 -> Porkf()
            else -> Dessertf()
        }
    }

    override fun getCount(): Int {
        return 8
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "早餐"
            1 -> "飯食"
            2 -> "麵食"
            3 -> "湯品"
            4 -> "配菜"
            5 -> "雞肉"
            6 -> "豬肉"
            else -> "甜點"
        }
    }
}