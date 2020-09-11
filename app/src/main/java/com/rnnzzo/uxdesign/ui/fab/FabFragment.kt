package com.rnnzzo.uxdesign.ui.fab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.databinding.FragmentFabBinding

class FabFragment : Fragment() {

    private lateinit var binding: FragmentFabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTabs()
    }

    fun setTabs(){
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.tabLayout.addTab(binding.tabLayout.newTab())
        binding.viewPager.adapter = FabAdapter()
    }

    inner class FabAdapter: PagerAdapter(){

        private val pageLayouts = listOf(R.layout.fab_simple, R.layout.fab_mini, R.layout.fab_extended)
        private val pageTitles = arrayOf("Simple","Mini", "Extended")

        override fun instantiateItem(container: ViewGroup, position: Int): Any =
            LayoutInflater.from(container.context).inflate(pageLayouts[position], container, false).apply {
                container.addView(this)
            }

        override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

        override fun getCount(): Int = pageLayouts.count()

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(`object` as View)

        override fun getPageTitle(position: Int): CharSequence? = pageTitles[position]
    }
}