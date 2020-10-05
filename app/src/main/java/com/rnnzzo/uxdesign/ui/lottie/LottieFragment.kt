package com.rnnzzo.uxdesign.ui.lottie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieDrawable
import com.rnnzzo.uxdesign.databinding.FragmentLottieBinding

class LottieFragment : Fragment() {

    private lateinit var binding: FragmentLottieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLottieBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initEvents()
    }

    fun initEvents(){
        var progress = 0
        binding.lottieAnimationView.addAnimatorUpdateListener {
            progress = (binding.lottieAnimationView.progress * 100).toInt() + 1
            binding.tvProgress.text = "$progress%"
            binding.progressBar.setProgress(progress)
        }
        binding.btnRestart.setOnClickListener {
            binding.lottieAnimationView.progress = 0f
            binding.lottieAnimationView.playAnimation()
        }
        binding.cbLoop.setOnCheckedChangeListener { compoundButton, checkedStated ->
            if(checkedStated){
                binding.lottieAnimationView.repeatCount = LottieDrawable.INFINITE
                binding.lottieAnimationView.playAnimation()
                binding.btnRestart.visibility = View.INVISIBLE
            }else{
                binding.lottieAnimationView.repeatCount = 0
                binding.btnRestart.visibility = View.VISIBLE
            }
        }
    }
}