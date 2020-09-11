package com.rnnzzo.uxdesign.ui.desing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.model.DesignItem

class DesignAdapter(var items: List<DesignItem>,
                    val clickListener: ClickListener
) : RecyclerView.Adapter<DesignAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvDesignText.text = item.title
        holder.ivDesignIcon.setImageResource(item.iconResource)
    }

    inner class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
        override fun onClick(v: View) {
            clickListener.onClickListener(adapterPosition, v)
        }

        val tvDesignText: TextView
        val ivDesignIcon: ImageView

        constructor(item: View) : super(item) {
            with(item) {
                tvDesignText = findViewById(R.id.tv_design_text)
                ivDesignIcon = findViewById(R.id.iv_design_icon)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface ClickListener {
        fun onClickListener(pos: Int, aView: View)
    }

    fun getItem(pos: Int) = items[pos]
}