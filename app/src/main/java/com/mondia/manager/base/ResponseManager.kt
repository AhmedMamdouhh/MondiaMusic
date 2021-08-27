package com.mondia.manager.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mondia.manager.connection.Resource
import com.mondia.manager.utilities.Event
import com.mondia.model.entity.home.Song

object ResponseManager
{
    private val resource= Resource<Any>()
    private val _observeResponseManager = MutableLiveData<Resource<Any>>()


    fun loading() { setResponseObject(Resource.ResourceStatus.LOADING, null) }
    fun hideLoading() { setResponseObject(Resource.ResourceStatus.HIDE_LOADING, null) }
    fun success(message: String?) { setResponseObject(Resource.ResourceStatus.SUCCESS, message) }
    fun failed(message: String?) { setResponseObject(Resource.ResourceStatus.FAILED, message) }
    fun noConnection() { setResponseObject(Resource.ResourceStatus.NO_CONNECTION, null) }


    private fun setResponseObject(status: Resource.ResourceStatus, message: String?){
        resource.resourceStatus = status
        resource.message = message
        _observeResponseManager.value = resource
        Log.e("safdsfdsf", "setResponseObject: ${resource.resourceStatus}" )
    }


    //getters
    val observeResponseManager :LiveData<Resource<Any>>
        get() = _observeResponseManager

}