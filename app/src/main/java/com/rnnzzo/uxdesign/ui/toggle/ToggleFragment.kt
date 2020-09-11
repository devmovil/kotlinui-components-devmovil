package com.rnnzzo.uxdesign.ui.toggle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.databinding.FragmentToggleBinding

class ToggleFragment : Fragment() {

    private lateinit var binding: FragmentToggleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToggleBinding.inflate(inflater)
        return binding.root
    }

}