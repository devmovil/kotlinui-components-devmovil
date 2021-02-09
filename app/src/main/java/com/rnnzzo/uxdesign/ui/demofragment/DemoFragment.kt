package com.rnnzzo.uxdesign.ui.demofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.R
import kotlinx.android.synthetic.main.fragment_demo.*

private const val ARG_DEMO_TITLE = "param1"
private const val ARG_IMAGE = "param2"

class DemoFragment : Fragment() {
    private var title: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            demoTitle.text = it.getString(ARG_DEMO_TITLE)
            demoImage.setImageResource(it.getInt(ARG_IMAGE))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, res: Int) =
            DemoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DEMO_TITLE, title)
                    putInt(ARG_IMAGE, res)
                }
            }
    }
}