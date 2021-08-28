package com.mondia.ui.response.no_connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mondia.R
import com.mondia.databinding.LayoutNoConnectionBinding
import com.mondia.manager.base.BaseBottomSheet

class NoConnectionSheet : BaseBottomSheet() {
    private lateinit var noConnectionBinding: LayoutNoConnectionBinding
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noConnectionBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_no_connection, container, false)
        noConnectionBinding.lifecycleOwner = this
        noConnectionBinding.clickListener = this

        return noConnectionBinding.root
    }

    fun onCloseClicked() {
        try {
            bottomSheetBehavior!!.isHideable = true
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
        } catch (ignored: NullPointerException) {
        }
    }


}

