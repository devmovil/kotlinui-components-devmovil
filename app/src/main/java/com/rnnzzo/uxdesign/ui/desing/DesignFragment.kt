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
import com.rnnzzo.uxdesign.ui.bottomsheetdialog.BottomSheetFragment
import com.rnnzzo.uxdesign.util.animOptions
import com.rnnzzo.uxdesign.util.shareApp


class DesignFragment: Fragment(), DesignAdapter.ClickListener {

    private lateinit var binding: FragmentDesignBinding
    private val adapter by lazy { DesignAdapter(getMenuItems(), this) }
    private var savedView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDesignBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return savedView ?: binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedView != null)
            return
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
        ),
        DesignItem(
            11,
            R.drawable.ic_sharp_more_horiz_24,
            "Bottom Nav",
            R.id.action_nav_designFragment_to_nav_bottomNavFragment
        ),
        DesignItem(
            12,
            R.drawable.ic_baseline_edit_attributes_24,
            "Chips",
            R.id.action_nav_designFragment_to_nav_bottomNavFragment
        ),
        DesignItem(
            13,
            R.drawable.ic_baseline_horizontal_rule_24,
            "Toolbar",
            R.id.action_nav_designFragment_to_nav_bottomNavFragment
        )
    )

    fun initRecyclerView(){
        binding.rvDesign.setHasFixedSize(true)
        binding.rvDesign.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvDesign.itemAnimator = DefaultItemAnimator()
        binding.rvDesign.adapter = adapter
    }

    fun bottomSheetClick(itemId: Int, selectedPos: Int){
        when(itemId){
            11 -> {
                val directions = arrayListOf(
                    R.id.action_nav_designFragment_to_nav_bottomNavFragment,
                    R.id.action_nav_designFragment_to_nav_shift_bottomFragment,
                    R.id.action_nav_designFragment_to_nav_icon_bottomFragment
                )
                findNavController().navigate(directions[selectedPos], null, animOptions)
            }
            12 -> {
                val directions = arrayListOf(
                    R.id.nav_chipGroupFragment,
                    R.id.nav_chipTypesFragment
                )
                findNavController().navigate(directions[selectedPos], null, animOptions)
            }
            13 -> {
                val directions = arrayListOf(
                    R.id.nav_basicToolbarFragment,
                    R.id.nav_collapseToolbarFragment,
                    R.id.nav_bottomToolbarFragment
                )
                findNavController().navigate(directions[selectedPos], null, animOptions)
            }
        }
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
        when (item.id) {
            11 -> {
                BottomSheetFragment.newInstance(
                    item.id,
                    arrayListOf("Basic", "Shift", "Icon"),
                    ::bottomSheetClick
                ).show(childFragmentManager, "BottomSheetDialog")
            }
            12 -> {
                BottomSheetFragment.newInstance(
                    item.id,
                    arrayListOf("Chip Group", "Chip Types"),
                    ::bottomSheetClick
                ).show(childFragmentManager, "BottomSheetDialog")
            }
            13 -> {
                BottomSheetFragment.newInstance(
                    item.id,
                    arrayListOf("Basic", "Collapse", "Bottom"),
                    ::bottomSheetClick
                ).show(childFragmentManager, "BottomSheetDialog")
            }
            else -> {
                findNavController().navigate(item.action, null, animOptions)
            }
        }
    }
}