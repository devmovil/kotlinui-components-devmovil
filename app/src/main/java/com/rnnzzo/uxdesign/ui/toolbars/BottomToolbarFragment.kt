package com.rnnzzo.uxdesign.ui.toolbars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rnnzzo.uxdesign.R
import kotlinx.android.synthetic.main.fragment_bottom_toolbar.*

class BottomToolbarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_toolbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (activity as AppCompatActivity)
        activity.let { activity ->
            activity.apply {
                supportActionBar?.hide()
            }
            bottom_toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
                activity.supportActionBar?.show()
            }
        }
        bottom_toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_basic_toolbar_search -> {
                    Toast.makeText(requireActivity(), "Search", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_basic_toolbar_more -> {
                    Toast.makeText(requireActivity(), "Options", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity.supportActionBar?.show()
            findNavController().navigateUp()
        }
    }
}