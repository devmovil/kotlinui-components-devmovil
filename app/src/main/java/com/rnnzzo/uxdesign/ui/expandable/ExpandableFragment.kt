package com.rnnzzo.uxdesign.ui.expandable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.databinding.FragmentExpandableBinding
import com.rnnzzo.uxdesign.util.ViewAnimation

class ExpandableFragment: Fragment() {

    private lateinit var binding: FragmentExpandableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpandableBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initEvents()
    }

    fun initEvents(){
        binding.btnArrowDownCard.setOnClickListener {
            toggle(binding.btnArrowDownCard, binding.lnExpandCard)
        }
        binding.btnArrowDownLinear.setOnClickListener {
            toggle(binding.btnArrowDownLinear, binding.lnExpandLinear)
        }
    }

    private fun toggle(arrow: View, container: View) {
        val show = toggleArrow(arrow)
        if (show) {
            ViewAnimation.expand(container)
        } else {
            ViewAnimation.collapse(container)
        }
    }

    fun toggleArrow(view: View): Boolean {
        return if (view.rotation == 0f) {
            view.animate().setDuration(200).rotation(180f)
            true
        } else {
            view.animate().setDuration(200).rotation(0f)
            false
        }
    }
}