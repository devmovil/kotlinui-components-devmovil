package com.rnnzzo.uxdesign.ui.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.snackbar.Snackbar
import com.rnnzzo.uxdesign.databinding.FragmentRecyclerviewBinding
import com.rnnzzo.uxdesign.model.RvItem
import com.rnnzzo.uxdesign.util.adapter.ItemTouchHelperCallback
import kotlinx.coroutines.*

class RecyclerViewFragment: Fragment(), ItemAdapter.ClickListener {

    private lateinit var binding: FragmentRecyclerviewBinding
    private val adapter by lazy { ItemAdapter(ArrayList(), this) }
    private var headerCount = 0
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        addData()
        initEvents()
    }

    fun initEvents(){
        binding.rvFab.setOnClickListener {

            val lm = binding.recyclerView.getLayoutManager() as LinearLayoutManager
            val pos = lm.findFirstVisibleItemPosition() + 1
            adapter.addItem( RvItem("${pos}", TYPE_ITEM), pos)

            Snackbar.make(
                binding.rvContainer,
                "Item added at ${pos}",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    fun initRecyclerView(){
        binding.recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setLayoutManager(linearLayoutManager)

        val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(adapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.addOnScrollListener(rvScrollListener)
    }

    fun addData(){
        val data: MutableList<RvItem> = ArrayList()
        headerCount++
        data.add(RvItem("Header ${headerCount}", TYPE_HEADER))
        for (i in 1..15){
            data.add(RvItem("${i}", TYPE_ITEM))
        }
        //ADD LOADER
        data.add(RvItem("", TYPE_LOADER))
        adapter.addData(data)
    }

    val rvScrollListener = object : OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                binding.rvFab.hide()
            } else {
                binding.rvFab.show()
            }

            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
            if (!isLoading && linearLayoutManager!!.itemCount == linearLayoutManager.findLastVisibleItemPosition() + 1) {
                loadData()
                isLoading = true
            }
        }
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main){
                adapter.removeLoader()
                addData()
                isLoading = false
            }
        }
    }

    override fun onClickListener(pos: Int, aView: View) {
        Snackbar.make(
            binding.rvContainer,
            "Item ${pos + 1} clicked",
            Snackbar.LENGTH_LONG
        ).show()
    }
}