package com.mondia.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mondia.manager.connection.WebServiceCallback
import com.mondia.manager.utilities.Event
import com.mondia.repository.SplashRepository

class SplashViewModel:ViewModel() , WebServiceCallback{

    private val splashRepository = SplashRepository()
    private val _observeToken = MutableLiveData<Event<String>>()



    fun callRepository(){
        splashRepository.getUserToken(this)
    }

    //TODO
    override fun onWebServiceSuccess(data: HashMap<String, ArrayList<*>>) {}

    override fun onWebServiceError(error: String) {
        _observeToken.value= Event(error)
    }

    //getters
    val observeToken: LiveData<Event<String>>
        get() = _observeToken
}