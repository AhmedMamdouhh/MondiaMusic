package com.mondia.manager.utilities

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.mondia.R


fun recyclerAnimationExtension(context: Context?, recyclerView: RecyclerView) {
    val resId: Int = R.anim.layout_animation
    val animation: LayoutAnimationController =
        AnimationUtils.loadLayoutAnimation(context, resId)
    recyclerView.layoutAnimation = animation

}