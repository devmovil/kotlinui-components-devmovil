package com.rnnzzo.uxdesign.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.model.RvItem
import com.rnnzzo.uxdesign.util.adapter.OnMoveAndSwipedListener
import java.util.*

val TYPE_HEADER = 1
val TYPE_ITEM = 2
val TYPE_LOADER = 3

class ItemAdapter(
    var items: MutableList<RvItem>,
    val clickListener: ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), OnMoveAndSwipedListener  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            TYPE_HEADER -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_header, parent, false)
                return HeaderViewHolder(view)
            }
            TYPE_ITEM -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recyclerview, parent, false)
                return ViewHolder(view)
            }
            else -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_loader, parent, false)
                return LoadingViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when(item.type){
            TYPE_ITEM -> {
                with(holder as ViewHolder) {
                    //Put your logic here
                }
            }
            TYPE_HEADER -> {
                with(holder as HeaderViewHolder) {
                    tvHeader.text = "${item.title}"
                }
            }
        }
    }

    override fun onItemMove(start: Int, end: Int): Boolean {
        Collections.swap(items, start, end)
        notifyItemMoved(start, end)
        return true
    }

    override fun onItemDismiss(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    inner class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
        override fun onClick(v: View) {
            clickListener.onClickListener(adapterPosition, v)
        }

        val ivIcon: ImageView
        val tvTitle: TextView
        val tvDescription: TextView

        constructor(item: View) : super(item) {
            with(item) {
                ivIcon = findViewById(R.id.iv_rv_appicon)
                tvTitle = findViewById(R.id.tv_title)
                tvDescription = findViewById(R.id.tv_description)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

     class HeaderViewHolder : RecyclerView.ViewHolder {
        val tvHeader: TextView
        constructor(item: View) : super(item) {
            with(item) {
                tvHeader = findViewById(R.id.tvHeader)
            }
        }
    }

     class LoadingViewHolder : RecyclerView.ViewHolder{
        val rvLoader: ProgressBar
        constructor(item: View) : super(item) {
            with(item) {
                rvLoader = findViewById(R.id.rvLoader)
            }
        }
    }

    interface ClickListener {
        fun onClickListener(pos: Int, aView: View)
    }

    fun getItem(pos: Int) = items[pos]

    fun addItem(extraItem:RvItem, pos: Int){
        items.add(pos, extraItem)
        notifyItemInserted(pos)
    }

    fun addData(extraItems: List<RvItem>){
        items.addAll(extraItems)
        notifyDataSetChanged()
    }

    fun removeLoader() {
        items.removeAt(items.size - 1)
        notifyItemRemoved(items.size)
    }

}