package com.rnnzzo.uxdesign.util.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rnnzzo.uxdesign.ui.recyclerview.TYPE_ITEM


class ItemTouchHelperCallback(listener: OnMoveAndSwipedListener) :
    ItemTouchHelper.Callback() {

    private val moveAndSwipedListener: OnMoveAndSwipedListener

    init {
        moveAndSwipedListener = listener
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (viewHolder.itemViewType == TYPE_ITEM) {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                makeMovementFlags(dragFlags, swipeFlags)
            } else {
                0
            }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // If the 2 items are not the same type, no dragging
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }
        moveAndSwipedListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        moveAndSwipedListener.onItemDismiss(viewHolder.adapterPosition)
    }
}
