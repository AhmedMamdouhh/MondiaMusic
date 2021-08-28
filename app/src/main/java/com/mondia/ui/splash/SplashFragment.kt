package com.mondia.ui.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.mondia.R
import com.mondia.databinding.FragmentSplashBinding
import com.mondia.manager.utilities.Constants
import com.mondia.manager.utilities.EventObserver
import com.mondia.manager.utilities.navigationExtension
import com.mondia.ui.home.HomeFragment


class SplashFragment : Fragment() {

    private lateinit var splashBinding: FragmentSplashBinding
    private val  splashViewModel = SplashViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        splashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        initializeStatusBar(R.color.colorPrimary)
        motionSplash()


        return splashBinding.root
    }

    private fun observeClientToken() {
        splashViewModel.observeToken.observe(viewLifecycleOwner, EventObserver { token ->
            Log.e("dsfsdlkjgbhjkds", "$token  " )

            splashBinding.progressSplashLoader.visibility = GONE
            navigationExtension(
                requireActivity().supportFragmentManager,
                HomeFragment(), Constants.HOME_FRAGMENT, Constants.TOKEN, token
            )
        })
    }

    private fun motionSplash() {

        Handler(Looper.getMainLooper()).postDelayed({
            splashBinding.mlSplashParent.setTransition(R.id.transition_splash_first)
            splashBinding.mlSplashParent.transitionToEnd()
        }, Constants.VERY_SMALL_DELAY.toLong())

        splashBinding.mlSplashParent.setTransitionListener(object :
            MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

                when (splashBinding.mlSplashParent.currentState) {
                    R.id.middle -> {
                        splashBinding.mlSplashParent.setTransition(R.id.transition_splash_second)
                        splashBinding.mlSplashParent.transitionToEnd()
                    }
                    R.id.end -> {
                        Log.e("dsfsdlkjgbhjkds", "onTransitionCompleted: " )
                        splashBinding.progressSplashLoader.visibility = VISIBLE
                        splashViewModel.callRepository()
                        observeClientToken()
                    }
                }

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
        })
    }

    private fun initializeStatusBar(color: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        }

    }


}