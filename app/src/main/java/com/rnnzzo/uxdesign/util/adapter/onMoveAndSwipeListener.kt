package com.rnnzzo.uxdesign.util.adapter

interface OnMoveAndSwipedListener {
    fun onItemMove(start: Int, end: Int): Boolean
    fun onItemDismiss(position: Int)
}