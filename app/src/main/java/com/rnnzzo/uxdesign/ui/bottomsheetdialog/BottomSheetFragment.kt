package com.rnnzzo.uxdesign.ui.bottomsheetdialog

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rnnzzo.uxdesign.R
import kotlinx.android.synthetic.main.fragment_bottom_sheet_list_dialog.*

const val ARG_ITEM_LIST = "item_list"
const val ARG_ITEM_ID = "id"

class BottomSheetFragment( val event: (Int, Int) -> Unit) : BottomSheetDialogFragment() {

    private var parentId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentId = arguments?.getInt(ARG_ITEM_ID) ?: 0
        bottomSheetList.layoutManager =
            LinearLayoutManager(context)
        bottomSheetList.adapter =
            arguments?.getStringArrayList(ARG_ITEM_LIST)?.let { ItemAdapter(it) }
    }

    private inner class ViewHolder constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.fragment_bottom_sheet_list_dialog_item,
            parent,
            false
        )
    ) {
        val text: TextView = itemView.findViewById(R.id.text)
    }

    private inner class ItemAdapter constructor(private val list: ArrayList<String>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = list[position]
            holder.text.setOnClickListener {
                this@BottomSheetFragment.event.invoke(parentId, position)
                this@BottomSheetFragment.dismiss()
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    companion object {
        fun newInstance(id: Int, list: ArrayList<String>, event:(Int, Int) -> Unit): BottomSheetFragment =
            BottomSheetFragment(event).apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_ITEM_LIST, list)
                    putInt(ARG_ITEM_ID, id)
                }
            }

    }
}