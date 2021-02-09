package com.rnnzzo.uxdesign.ui.chips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.rnnzzo.uxdesign.R
import kotlinx.android.synthetic.main.fragment_chip_group.*

class ChipGroupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chip_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val desserts = resources.getStringArray(R.array.desserts)
        desserts.forEach { text ->
            val chip = layoutInflater.inflate(R.layout.simple_chip_item, chipGroup, false) as Chip
            chip.text = text
            chip.setOnClickListener {
                chipText.text = text.toString()
            }
            chipGroup.addView(chip)
        }
    }

}