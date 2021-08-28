package com.mondia.manager.utilities

import android.content.Context
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.mondia.R


fun recyclerAnimationExtension(context: Context?, recyclerView: RecyclerView) {
    val resId: Int = R.anim.layout_animation
    val animation: LayoutAnimationController =
        AnimationUtils.loadLayoutAnimation(context, resId)
    recyclerView.layoutAnimation = animation

}

fun navigationExtension(supportFragmentManager:FragmentManager,fr: Fragment,
                        tag:String,argsKey:String, argsValue:String){
    var fragment: Fragment?

    val args = Bundle()
    args.putString(argsKey, argsValue)

    val fm = supportFragmentManager
    fragment = fm.findFragmentByTag(tag)
    if (fragment == null) {
        val ft: FragmentTransaction = fm.beginTransaction()
        fragment = fr
        fragment.arguments = args
        ft.replace(android.R.id.content, fragment, tag)
        ft.commit()
    }
}
