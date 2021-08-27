package com.mondia.manager.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mondia.R
import com.mondia.databinding.ActivityMainBinding
import com.mondia.ui.home.HomeFragment


class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navigation()
    }

    fun navigation(){
        var fragment: Fragment?

        val fm = supportFragmentManager
        fragment = fm.findFragmentByTag("myFragmentTag")
        if (fragment == null) {
            val ft: FragmentTransaction = fm.beginTransaction()
            fragment = HomeFragment()
            ft.add(android.R.id.content, fragment, "myFragmentTag")
            ft.commit()
        }
    }
}