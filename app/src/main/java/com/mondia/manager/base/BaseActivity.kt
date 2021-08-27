package com.mondia.manager.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mondia.R
import com.mondia.manager.connection.Resource
import com.mondia.manager.utilities.Constants
import com.mondia.manager.utilities.EventObserver
import com.mondia.ui.response.error.ErrorSheet
import com.mondia.ui.response.no_connection.NoConnectionSheet
import com.mondia.ui.response.success.SuccessSheet

abstract class BaseActivity : AppCompatActivity() {

    protected var loadingBar: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeProgress()
        observeResponse()
    }


    open fun observeResponse() {
        ResponseManager.observeResponseManager.observe(this,
            Observer { responseResource ->
                responseResource as Resource<*>
                try {
                    when (responseResource.resourceStatus) {
                        Resource.ResourceStatus.LOADING -> {
                            showProgress()
                        }
                        Resource.ResourceStatus.HIDE_LOADING -> {
                            hideProgress()
                        }
                        Resource.ResourceStatus.SUCCESS -> {
                            hideProgress()
                            successMessage(responseResource.message)
                        }
                        Resource.ResourceStatus.FAILED -> {
                            hideProgress()
                            failedMessage(responseResource.message)
                        }
                        Resource.ResourceStatus.NO_CONNECTION -> {
                            hideProgress()
                            noConnection()
                        }

                    }
                } catch (ex: NullPointerException) {
                }
            })
    }

    //Snack bar :
    private fun successMessage(message: String?) {
        val successSheet = SuccessSheet()
        val bundle = Bundle()
        bundle.putString(Constants.MESSAGE, message)
        successSheet.arguments = bundle

        successSheet.show(supportFragmentManager, Constants.SUCCESS_SHEET)
    }

    private fun failedMessage(message: String?) {
        val errorSheet = ErrorSheet()
        val bundle = Bundle()
        bundle.putString(Constants.MESSAGE, message)
        errorSheet.arguments = bundle

        errorSheet.show(supportFragmentManager, Constants.ERROR_SHEET)
    }

    private fun noConnection() {
        NoConnectionSheet().show(supportFragmentManager, Constants.NO_CONNECTION_SHEET)
    }

    private fun initializeProgress() {
        loadingBar = Dialog(this, R.style.Theme_AppCompat_DayNight)
        loadingBar!!.setCancelable(false)
        loadingBar!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = LayoutInflater.from(this)
        val loadingView = inflater.inflate(R.layout.layout_loader, null)
        loadingBar!!.setContentView(loadingView)
        loadingBar!!.window!!.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#0effffff"))
        )
    }

    private fun showProgress() {
        if (loadingBar != null && !this.isFinishing) loadingBar!!.show()
    }

    private fun hideProgress() {
        if (loadingBar != null && loadingBar!!.isShowing && !this.isFinishing) loadingBar!!.dismiss()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}