package com.mondia.manager.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mondia.R
import com.mondia.databinding.ActivityMainBinding
import com.mondia.manager.utilities.Constants
import com.mondia.manager.utilities.navigationExtension
import com.mondia.ui.splash.SplashFragment


class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navigationExtension(supportFragmentManager, SplashFragment(),Constants.SPLASH_FRAGMENT,"","")
    }


}