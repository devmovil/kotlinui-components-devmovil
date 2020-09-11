package com.rnnzzo.uxdesign.ui.ads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.rnnzzo.uxdesign.databinding.FragmentAdsBinding

class AdsFragment : Fragment() {

    private lateinit var binding: FragmentAdsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdsBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeAds()
    }

    fun initializeAds(){
        val adRequest = AdRequest.Builder().build()
        binding.bannerSimple.loadAd(adRequest)
        binding.bannerLarge.loadAd(adRequest)
        binding.bannerMediumRectanble.loadAd(adRequest)
        binding.bannerSmart.loadAd(adRequest)
        binding.bannerLeaderboard.loadAd(adRequest)
        binding.bannerFull.loadAd(adRequest)
    }

}