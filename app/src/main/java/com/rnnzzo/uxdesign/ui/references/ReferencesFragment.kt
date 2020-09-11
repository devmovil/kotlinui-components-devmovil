package com.rnnzzo.uxdesign.ui.references

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.databinding.FragmentReferencesBinding

class ReferencesFragment: Fragment() {

    private lateinit var binding: FragmentReferencesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReferencesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadReferences()
    }

    fun loadReferences(){
        binding.webView.loadUrl("file:///android_asset/references.html")
    }
}