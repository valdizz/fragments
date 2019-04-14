package com.valdizz.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Application with fragments:
 * in the portrait orientation contains a single FragmentA,
 * in landscape orientation contains FragmentA and FragmentB.
 * @autor Vlad Kornev
 * @version 1.0
 */
class MainActivity : AppCompatActivity(), FragmentA.OnButtonClickListener {

    private var clickCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickCounter = savedInstanceState?.getInt(CLICK_COUNTER_KEY) ?: 0
        createFragments(clickCounter)
    }

    private fun createFragments(numOfClicks: Int) {
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_a_container, FragmentA.newInstance())
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.inTransaction {
                replace(R.id.fragment_b_container, FragmentB.newInstance(numOfClicks))
            }
        }
    }

    private fun replaceFragment(numOfClicks: Int) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragment_b_container) as FragmentB?
            if (fragmentB != null) {
                fragmentB.setNumOfClicks(numOfClicks)
            }
        } else {
            supportFragmentManager.inTransaction {
                replace(R.id.fragment_a_container, FragmentB.newInstance(numOfClicks)).addToBackStack(null)
            }

        }
    }

    override fun onButtonClick() {
        replaceFragment(++clickCounter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLICK_COUNTER_KEY, clickCounter)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    private companion object {
        private const val CLICK_COUNTER_KEY = "ClickCounterKey"
    }
}