package com.example.testpopup.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.testpopup.ImageFragment
import com.example.testpopup.model.Photo

class ViewPagerAdapter(fragmentManager: FragmentManager, private val photos: ArrayList<Photo>) :
    FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(photos[position].url)
    }

    // 3
    override fun getCount(): Int {
        return photos.size
    }
}