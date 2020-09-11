package com.rnnzzo.uxdesign.ui.illustrations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.databinding.FragmentIllustrationsBinding
import com.rnnzzo.uxdesign.util.addProgressDots

class IllustrationsFragment : Fragment() {

    private lateinit var binding: FragmentIllustrationsBinding
    private val adapter = ImageSliderAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIllustrationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    fun initViewPager(){
        binding.layoutDots.addProgressDots(0, adapter.count)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(pagerChangeListener)
    }

    val pagerChangeListener = object :ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}
        override fun onPageSelected(position: Int) {
            binding.layoutDots.addProgressDots(position, adapter.count)
        }
    }

    inner class ImageSliderAdapter: PagerAdapter(){

        private val images = listOf(R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4)
        private val pageTitles = arrayOf("Simple","Mini", "Extended")

        override fun instantiateItem(container: ViewGroup, position: Int): Any =
            LayoutInflater.from(container.context).inflate(R.layout.item_image_slider, container, false).apply {
                val image = findViewById(R.id.imageSlide) as ImageView
                image.setImageResource(images[position])
                container.addView(this)
            }

        override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

        override fun getCount(): Int = images.count()

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(`object` as View)

        override fun getPageTitle(position: Int): CharSequence? = pageTitles[position]
    }

}