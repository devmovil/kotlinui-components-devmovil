package com.rnnzzo.uxdesign.ui.bottomnav

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.ui.demofragment.DemoFragment
import kotlinx.android.synthetic.main.fragment_bottom_nav.*


class BottomNavShiftFragment : Fragment() {

    val fragment1 = DemoFragment.newInstance("Fragment 1", R.drawable.slide_1)
    val fragment2 = DemoFragment.newInstance("Fragment 2", R.drawable.slide_2)
    val fragment3 = DemoFragment.newInstance("Fragment 3", R.drawable.slide_3)
    val fragment4 = DemoFragment.newInstance("Fragment 4", R.drawable.slide_4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_nav_shift, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initEvents()
    }

    fun initEvents(){
        setFragment(fragment1)
        changeColor("#455A64")
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_menu_one -> {
                    setFragment(fragment1)
                    changeColor("#455A64")
                }
                R.id.bottom_nav_menu_two -> {
                    setFragment(fragment2)
                    changeColor("#AD1457")
                }
                R.id.bottom_nav_menu_three -> {
                    setFragment(fragment3)
                    changeColor("#616161")
                }
                R.id.bottom_nav_menu_four -> {
                    setFragment(fragment4)
                    changeColor("#00695C")
                }
                else -> {
                    false
                }
            }
            true
        }
    }

    fun setFragment(fragment: Fragment){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerBottomNav, fragment)
            .commit()
    }

    fun changeColor(color: String){
        bottomNav.setBackgroundColor(Color.parseColor(color))
    }
}