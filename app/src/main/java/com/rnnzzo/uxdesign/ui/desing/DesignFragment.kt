package com.rnnzzo.uxdesign.ui.desing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.databinding.FragmentDesignBinding
import com.rnnzzo.uxdesign.model.DesignItem
import com.rnnzzo.uxdesign.util.animOptions
import com.rnnzzo.uxdesign.util.shareApp


class DesignFragment: Fragment(), DesignAdapter.ClickListener {

    private lateinit var binding: FragmentDesignBinding
    private val adapter by lazy { DesignAdapter(getMenuItems(), this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDesignBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    fun getMenuItems() = listOf(
        DesignItem(
            1,
            R.drawable.ic_baseline_note,
            "Cards",
            R.id.action_nav_designFragment_to_cardsFragment
        ),
        DesignItem(
            2,
            R.drawable.ic_dialog,
            "Dialogs",
            R.id.action_nav_designFragment_to_dialogsFragment
        ),
        DesignItem(
            3,
            R.drawable.ic_baseline_text,
            "EditText",
            R.id.action_nav_designFragment_to_editTextFragment
        ),
        DesignItem(
            4,
            R.drawable.ic_baseline_toggle_on,
            "Toggle",
            R.id.action_nav_designFragment_to_toggleFragment
        ),
        DesignItem(
            5,
            R.drawable.ic_baseline_touch_app,
            "Buttons",
            R.id.action_nav_designFragment_to_buttonsFragment
        ),
        DesignItem(
            6,
            R.drawable.ic_baseline_add_circle,
            "Fab",
            R.id.action_nav_designFragment_to_nav_fabFragment
        ),
        DesignItem(
            7,
            R.drawable.ic_reload,
            "Progress",
            R.id.action_nav_designFragment_to_nav_progressFragment
        ),
        DesignItem(
            8,
            R.drawable.ic_baseline_amp,
            "Image Slide",
            R.id.action_nav_designFragment_to_nav_illustrationsFragment
        ),
        DesignItem(9, R.drawable.ic_ad, "Ads", R.id.action_nav_designFragment_to_nav_adsFragment),
        DesignItem(
            10,
            R.drawable.ic_baseline_vertical_align,
            "Expandable View",
            R.id.action_nav_designFragment_to_nav_expandableFragment
        )
    )

    fun initRecyclerView(){
        binding.rvDesign.setHasFixedSize(true)
        binding.rvDesign.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvDesign.itemAnimator = DefaultItemAnimator()
        binding.rvDesign.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_more, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_share -> {
                shareApp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickListener(pos: Int, aView: View) {
        val item = adapter.getItem(pos)
        findNavController().navigate(item.action, null, animOptions)
    }
}