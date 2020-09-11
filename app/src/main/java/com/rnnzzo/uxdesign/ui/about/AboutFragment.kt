package com.rnnzzo.uxdesign.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.BuildConfig
import com.rnnzzo.uxdesign.databinding.FragmentAboutBinding
import com.rnnzzo.uxdesign.util.APP_PLAY_STORE_URL
import com.rnnzzo.uxdesign.util.SOURCE_CODE_URL

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setData()
        initEvents()
    }

    fun setData(){
        binding.tvVersion.text = BuildConfig.VERSION_NAME
    }

    fun initEvents(){
        binding.btnRateApp.setOnClickListener {
            val intent = Intent()
            intent.setData(Uri.parse(APP_PLAY_STORE_URL))
            intent.setAction(Intent.ACTION_VIEW)
            startActivity(intent)
        }
        binding.btnGetSourceCode.setOnClickListener {
            val intent = Intent()
            intent.setData(Uri.parse(SOURCE_CODE_URL))
            intent.setAction(Intent.ACTION_VIEW)
            startActivity(intent)
        }
    }
}