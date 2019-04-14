package com.valdizz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_b.*

class FragmentB : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onStart() {
        super.onStart()
        arguments?.getInt(CLICK_COUNTER_ARGS)?.let {
            setNumOfClicks(it)
        }
    }

    fun setNumOfClicks(numOfClicks: Int) {
        tvBtnClickCounter.text = numOfClicks.toString()
    }

    companion object {
        private const val CLICK_COUNTER_ARGS = "ClickCounterArgs"

        fun newInstance(numOfClicks: Int): FragmentB {
            val args = Bundle().apply { putInt(CLICK_COUNTER_ARGS, numOfClicks) }
            val fragment = FragmentB().apply { arguments = args }
            return fragment
        }
    }
}