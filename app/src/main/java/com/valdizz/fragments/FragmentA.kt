package com.valdizz.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*
import java.lang.ClassCastException

class FragmentA : Fragment() {

    private lateinit var onButtonClickListener: OnButtonClickListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnButtonClickListener) {
            onButtonClickListener = context
        }
        else {
            throw ClassCastException(context.toString() + " must implement OnButtonClickListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener { onButtonClickListener.onButtonClick() }
    }

    interface OnButtonClickListener {
        fun onButtonClick()
    }

    companion object {
        fun newInstance() = FragmentA()
    }
}